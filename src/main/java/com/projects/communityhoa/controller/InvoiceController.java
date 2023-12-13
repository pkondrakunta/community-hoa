/**
 * Invoice controller for the Community HOA application. Maps and 
 * manages different actions for Invoices (for HOA_STAFF management)
 * 
 * @author Pragnya Kondrakunta
 */

package com.projects.communityhoa.controller;

import java.io.IOException;
import java.time.LocalDateTime;
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

		Invoice invoice = new Invoice();
		LocalDateTime ldt = LocalDateTime.now();

		invoice.setMemberID(memberId);
		invoice.setDate(ldt.now());
		invoice.setLandscaping(0.0);
		invoice.setLawnMowing(0.0);
		invoice.setSnowRemoval(0.0);
		invoice.setLandscaping(0.0);

		invoice.setWater(Double.parseDouble(water));
		invoice.setTrash(Double.parseDouble(trash));

		invoice.setTotal(Double.parseDouble(total));

		System.out.print(invoice);
		invoiceService.save(invoice);
		request.setAttribute("invoice", invoice);
		request.setAttribute("payment_type", "utilities");
		

		return "paymentSuccess";
	}

	@GetMapping("/invoice/{invoiceId}/export")
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
