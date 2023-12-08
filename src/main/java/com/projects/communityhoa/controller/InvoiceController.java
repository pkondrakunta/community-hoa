/**
 * Invoice controller for the Community HOA application. Maps and 
 * manages different actions for Invoices (for HOA_STAFF management)
 * 
 * @author Pragnya Kondrakunta
 */

package com.projects.communityhoa.controller;

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

import com.projects.communityhoa.model.Invoice;
import com.projects.communityhoa.model.Member;

import jakarta.servlet.http.HttpServletRequest;
//import jakarta.validation.constraints.Min;
//import jakarta.validation.constraints.NotNull;
//import jakarta.validation.constraints.Size;
//import jakarta.validation.constraints.*;

import com.projects.communityhoa.service.InvoiceService;
import com.projects.communityhoa.service.MemberService;

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

}
