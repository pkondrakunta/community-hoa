/**
 * Main controller for the Community HOA application. Maps and 
 * manages different actions 
 * 
 * @author Pragnya Kondrakunta
 */

package com.projects.communityhoa.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.support.SessionStatus;

import com.projects.communityhoa.model.Invoice;
import com.projects.communityhoa.model.Member;

import jakarta.servlet.http.HttpServletRequest;

import com.projects.communityhoa.service.InvoiceService;
import com.projects.communityhoa.service.MemberService;

@Controller
public class HomeController {
	
	@Autowired
	InvoiceService invoiceService;
	
	@Autowired
	MemberService memberService;

	@GetMapping("/login")
	public String handleLogin() {
		// Retrieve members from database
		return "member_list";
	}

	@GetMapping("/members")
	public String handleGetMembers() {
		return "members";
	}

	@GetMapping("/searchMember")
	public String showSearchMemberForm() {
		return "searchMember";
	}

	@PostMapping("/searchMember")
	public String searchMember(@ModelAttribute("member-text") String search_text, SessionStatus status,
			HttpServletRequest request) {
		List<Member> searchMemberResults = memberService.searchMembers(search_text);

		if (searchMemberResults.isEmpty()) {
			return ("noMatchingMembers");
		} else {
			return ("searchMemberResults");
		}
	}

	@GetMapping("/searchInvoice")
	public String showSearchInvoiceForm() {
		return "searchInvoice";
	}
	
	@GetMapping("/invoices")
	public String handleGetInvoices() {
		return "invoices";
	}

	@PostMapping("/searchInvoice")
	public String searchInvoice(@ModelAttribute("invoice-text") String search_text, SessionStatus status,
			HttpServletRequest request) {
		List<Invoice> searchInvoiceResults = invoiceService.searchInvoices(search_text);

		if (searchInvoiceResults.isEmpty()) {
			return ("noMatchingInvoices");
		} else {
			return ("searchInvoiceResults");
		}

	}
	
	@GetMapping("/addMember")
	public String showAddMemberForm() {
		return "addMember";
	}
	
	@GetMapping("/*/memberView")
	public String showMemberView() {
		return "memberView";
	}

}
