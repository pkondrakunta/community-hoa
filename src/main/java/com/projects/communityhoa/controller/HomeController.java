/**
 * Main controller for the Community HOA application. Maps and 
 * manages different actions 
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
	public String handleGetMembers(HttpServletRequest request) {
		List<Member> allMembersList = memberService.getAllMembers();
		request.setAttribute("memberList", allMembersList);

		return "members";
	}

	@GetMapping("/searchMember")
	public String showSearchMemberForm(HttpServletRequest request) {
		request.setAttribute("resultsOutcome", "null");
		return "searchMember";
	}

	@PostMapping("/searchMember")
	public String searchMember(@RequestParam(name = "member-search-text") @NonNull String search_text,
			SessionStatus status, HttpServletRequest request) {
		String search_with_wildcard = "%".concat(search_text).concat("%");
		request.setAttribute("sText", search_text);

		List<Member> searchMemberResults = memberService.searchMembers(search_with_wildcard);

		if (searchMemberResults.isEmpty()) {
			request.setAttribute("resultsOutcome", "false");
		} else {
			request.setAttribute("resultsOutcome", "true");
			request.setAttribute("memberResultList", searchMemberResults);
		}

		return "searchMember";

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
			request.setAttribute("resultsOutcome", "false");
		} else {
			request.setAttribute("resultsOutcome", "true");
		}
		return "searchMemberResults";
	}

	@GetMapping("/addMember")
	public String showAddMemberForm() {
		return "addMember";
	}

	@PostMapping("/addMember")
	public String addMemberAndShowSuccess(HttpServletRequest request,
			@RequestParam(name = "inputFirstName") @NonNull String firstName,
			@RequestParam(name = "inputLastName") @NonNull String lastName,
			@RequestParam(name = "inputEmail") @NonNull String email,
			@RequestParam(name = "inputPhone") @NonNull String phone,
			@RequestParam(name = "inputAddress") @NonNull String address,
			@RequestParam(name = "inputUnit") @NonNull String unit,
			@RequestParam(name = "inputUnitType") @NonNull String unitType,
			@RequestParam(name = "inputSubscriptionPlan") @NonNull String subscriptionPlan) {

		// Have to set memberID, last paid, expiry in service - until next cycle (+1
		// year for annual or +1 month for monthly)

		Member memObj = new Member();

		memObj.setFirstName(firstName);
		memObj.setLastName(lastName);
		memObj.setUnit(unit);
		memObj.setUnitType(unitType);
		memObj.setPhone(phone);
		memObj.setSubscriptionPlan(subscriptionPlan);
		memObj.setEmail(email);
		memObj.setAddress(address);

		Member addedMember = memberService.addMember(memObj);
		request.setAttribute("member", addedMember);

		return "addMemberSuccess";
	}

	@GetMapping("/member/{memberId}")
	public String showMemberView(HttpServletRequest request, @PathVariable(name = "memberId") String memberId) {
		Member member = memberService.getMemberfromID(memberId);
		request.setAttribute("member", member);
		return "member";
	}

}
