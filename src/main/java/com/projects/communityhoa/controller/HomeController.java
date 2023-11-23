package com.projects.communityhoa.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.projects.communityhoa.model.Invoice;
import com.projects.communityhoa.model.Member;

@Controller
public class HomeController {

	@GetMapping("/login")
	public String handleLogin() {
		// Retrieve members from db
		return "member_list";
	}

	@GetMapping("/members")
	@ResponseBody
	public String handleGetMembers() {
		// Retrieve members from db
		return "member_list";
	}

	@GetMapping("/search-member")
	public String showSearchMemberForm() {
		return "search-member-form";
	}

	@GetMapping("/search-invoice")
	public String showSearchInvoiceForm() {
		return "search-invoice-form";
	}

}
