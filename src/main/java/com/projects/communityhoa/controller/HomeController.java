package com.projects.communityhoa.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;

import com.projects.communityhoa.model.Invoice;
import com.projects.communityhoa.model.Member;

import jakarta.servlet.http.HttpServletRequest;
import com.projects.communityhoa.service.SearchService;

@Controller
public class HomeController {

	@Autowired
	SearchService searchService;

	@GetMapping("/login")
	public String handleLogin() {
		// Retrieve members from database
		return "member_list";
	}

	@GetMapping("/members")
	public String handleGetMembers() {
		return "members";
	}

	@GetMapping("/search-member")
	public String showSearchMemberForm() {
		return "search-member";
	}

	@PostMapping("/search-member")
	public String searchMember(@ModelAttribute("member-text") String search_text, SessionStatus status,
			HttpServletRequest request) {
		List<Member> searchMemberResults = searchService.searchMember(search_text);

		if (searchMemberResults.isEmpty()) {
			return ("no-matches-members");
		} else {
			return ("search-results-members");
		}
	}

	@GetMapping("/search-invoice")
	public String showSearchInvoiceForm() {
		return "search-invoice";
	}

	@PostMapping("/search-invoice")
	public String searchInvoice(@ModelAttribute("invoice-text") String search_text, SessionStatus status,
			HttpServletRequest request) {
		List<Invoice> searchInvoiceResults = searchService.searchInvoice(search_text);

		if (searchInvoiceResults.isEmpty()) {
			return ("no-matches-invoices");
		} else {
			return ("search-results-invoices");
		}

	}

}
