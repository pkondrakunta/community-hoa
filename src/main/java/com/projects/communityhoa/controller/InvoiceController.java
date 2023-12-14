/**
 * Invoice controller for the Community HOA application. Maps and 
 * manages different actions for Invoices (for HOA_STAFF management)
 * 
 * @author Pragnya Kondrakunta
 */

package com.projects.communityhoa.controller;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.support.SessionStatus;

import com.lowagie.text.DocumentException;
import com.lowagie.text.pdf.PdfException;
import com.projects.communityhoa.model.Invoice;
import com.projects.communityhoa.model.Member;

import jakarta.servlet.http.HttpServletRequest;
//import jakarta.validation.constraints.Min;
//import jakarta.validation.constraints.NotNull;
//import jakarta.validation.constraints.Size;
//import jakarta.validation.constraints.*;
import jakarta.servlet.http.HttpServletResponse;

import com.projects.communityhoa.service.InvoiceService;
import com.projects.communityhoa.service.MemberService;
import com.projects.communityhoa.util.InvoicePDFExporter;
import com.projects.communityhoa.util.MembersPDFExporter;

@Controller
public class InvoiceController {

	@Autowired
	private InvoiceService invoiceService;

	@Autowired
	private MemberService memberService;

	@GetMapping("/invoices")
	public String handleGetInvoices(HttpServletRequest request) {
		List<Invoice> allInvoicesList = invoiceService.getAllInvoices();
		request.setAttribute("invoiceList", allInvoicesList);

		return "invoices";
	}

	@GetMapping("/searchInvoice")
	public String showSearchInvoiceForm(HttpServletRequest request) {
		request.setAttribute("resultsOutcome", "null");
		return "searchInvoice";
	}

	@PostMapping("/searchInvoice")
	public String searchInvoice(@ModelAttribute("invoice-search-text") String search_text, SessionStatus status,
			HttpServletRequest request) {
		request.setAttribute("sText", search_text);

		List<Invoice> searchInvoiceResults = invoiceService.getSearchInvoices(search_text);

		if (searchInvoiceResults.isEmpty()) {
			request.setAttribute("resultsOutcome", "false");
		} else {
			request.setAttribute("resultsOutcome", "true");
			request.setAttribute("invoiceResultList", searchInvoiceResults);
		}

		return "searchMember";
	}

	@PostMapping("/member/{memberId}/confirmUtilitiesPayment")
	public String confirmUtilitiesPayment(HttpServletRequest request, @PathVariable(name = "memberId") String memberId,
			@RequestParam(name = "subscriptionNewValidity") @NonNull String subscriptionNewValidity,
			@RequestParam(name = "water") @NonNull String water,
			@RequestParam(name = "trash") @NonNull String trash,
			@RequestParam(name = "total") @NonNull String total) {

		Invoice invoiceObj = new Invoice();
		LocalDateTime ldt = LocalDateTime.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd, yyyy"); 
		LocalDate newExpiry = LocalDate.parse(subscriptionNewValidity, formatter);
		
		invoiceObj.setMemberID(memberId);
		invoiceObj.setDate(ldt);
		invoiceObj.setLandscaping(0.0);
		invoiceObj.setLawnMowing(0.0);
		invoiceObj.setSnowRemoval(0.0);
		invoiceObj.setLandscaping(0.0);
		invoiceObj.setWater(Double.parseDouble(water));
		invoiceObj.setTrash(Double.parseDouble(trash));
		invoiceObj.setTotal(Double.parseDouble(total));
		invoiceObj.setNewExpiry(newExpiry);

		Invoice createdInvoice = invoiceService.save(invoiceObj);
		
		//Update member table with latest subscription details
		
		Member member = memberService.getMemberById(memberId);
		member.setLastPaid(ldt);
		member.setSubscriptionExpiry(newExpiry);
		
		memberService.update(member);
		
		request.setAttribute("invoice", createdInvoice);
		request.setAttribute("payment_type", "utilities");

		return "paymentSuccess";
	}

	@GetMapping("/invoice/{invoiceId}/download")
	public void exportToPDF(HttpServletResponse response, @PathVariable(name = "invoiceId") String invoiceId)
			throws PdfException, DocumentException, IOException {
		response.setContentType("application/pdf");

		String headerKey = "Content-Disposition";
		String headerValue = "attachment; filename=invoice_" + invoiceId + ".pdf";
		response.setHeader(headerKey, headerValue);

		Invoice invoice_to_export = invoiceService.getInvoiceById(invoiceId);

		InvoicePDFExporter exporter = new InvoicePDFExporter(invoice_to_export);
		exporter.export(response);

	}
}
