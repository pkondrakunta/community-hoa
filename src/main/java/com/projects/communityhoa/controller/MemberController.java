/**
 * Member controller for the Community HOA application. Maps and 
 * manages different actions for Invoices (for HOA_STAFF management)
 * 
 * @author Pragnya Kondrakunta
 */

package com.projects.communityhoa.controller;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.support.SessionStatus;

import com.lowagie.text.DocumentException;
import com.lowagie.text.pdf.PdfException;
import com.projects.communityhoa.model.Fee;
import com.projects.communityhoa.model.Member;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import com.projects.communityhoa.service.FeeService;
import com.projects.communityhoa.service.MemberService;
import com.projects.communityhoa.util.MembersPDFExporter;

@Controller
public class MemberController {

	@Autowired
	private MemberService memberService;

	@Autowired
	private FeeService feeService;

	@GetMapping("/members")
	public String handleGetMembers(HttpServletRequest request) {
		List<Member> allMembersList = memberService.getAllMembers();
		request.setAttribute("memberList", allMembersList);

		return "members";
	}

	@GetMapping({"/searchMember", "/home"})
	public String showSearchMemberForm(HttpServletRequest request) {
		request.setAttribute("resultsOutcome", "null");
		return "searchMember";
	}

	@PostMapping("/searchMember")
	public String searchMember(@RequestParam(name = "member-search-text") @NonNull String search_text,
			SessionStatus status, HttpServletRequest request) {
		request.setAttribute("sText", search_text);

		List<Member> searchMemberResults = memberService.getSearchMembers(search_text);

		if (searchMemberResults.isEmpty()) {
			request.setAttribute("resultsOutcome", "false");
		} else {
			request.setAttribute("resultsOutcome", "true");
			request.setAttribute("memberResultList", searchMemberResults);
		}

		return "searchMember";

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

		Member addedMember = memberService.save(memObj);
		request.setAttribute("member", addedMember);
		request.setAttribute("action", "added");

		return "memberActionSuccess";
	}

	@GetMapping("/member/{memberId}")
	public String showMemberView(HttpServletRequest request, @PathVariable(name = "memberId") String memberId) {
		Member member = memberService.getMemberById(memberId);
		request.setAttribute("member", member);

		// Setting fees for member
		List<Fee> fee = feeService.getAllFees();
		request.setAttribute("feeList", fee);
		return "member";
	}

	@GetMapping("/member/{memberId}/update")
	public String showUpdateMemberView(HttpServletRequest request, @PathVariable(name = "memberId") String memberId) {
		Member member = memberService.getMemberById(memberId);
		request.setAttribute("member", member);
		return "updateMember";
	}

	@PostMapping("/member/{memberId}/update")
	public String updateMemberAndShowSuccess(HttpServletRequest request,
			@PathVariable(name = "memberId") String memberId,
			@RequestParam(name = "inputFirstName") @NonNull String firstName,
			@RequestParam(name = "inputLastName") @NonNull String lastName,
			@RequestParam(name = "inputEmail") @NonNull String email,
			@RequestParam(name = "inputPhone") @NonNull String phone,
			@RequestParam(name = "inputAddress") @NonNull String address,
			@RequestParam(name = "inputUnit") @NonNull String unit,
			@RequestParam(name = "inputUnitType") @NonNull String unitType,
			@RequestParam(name = "inputSubscriptionPlan") @NonNull String subscriptionPlan) {

		Member memObj = memberService.getMemberById(memberId);

		memObj.setFirstName(firstName);
		memObj.setLastName(lastName);
		memObj.setUnit(unit);
		memObj.setUnitType(unitType);
		memObj.setPhone(phone);
		memObj.setSubscriptionPlan(subscriptionPlan);
		memObj.setEmail(email);
		memObj.setAddress(address);

		memberService.update(memObj);
		request.setAttribute("member", memObj);
		request.setAttribute("action", "updated");

		return "memberActionSuccess";

	}

	@GetMapping("/member/{memberId}/delete")
	public String updateMemberAndShowSuccess(HttpServletRequest request,
			@PathVariable(name = "memberId") String memberId) {
		Member member = memberService.getMemberById(memberId);
		memberService.delete(member);
		request.setAttribute("memberID", member.getMemberID());
		request.setAttribute("action", "deleted");
		return "memberActionSuccess";
	}

	@GetMapping("/member/{memberId}/payUtilities")
	public String showPayUtilityView(HttpServletRequest request, @PathVariable(name = "memberId") String memberId) {
		Member member = memberService.getMemberById(memberId);
		request.setAttribute("member", member);
		request.setAttribute("chargesBreakdown", "false");
		String plan = member.getSubscriptionPlan();
		if (member.getSubscriptionPlan().equals("Annually")) {
			List<String> yearList = generateYearlyDisplayList(member.getSubscriptionExpiry(),3);
			request.setAttribute("yearList", yearList);
		}
		else {
			List<String> monthList = generateMonthlyDisplayList(member.getSubscriptionExpiry(),12);
			request.setAttribute("monthList", monthList);
		}
		
		return "payUtilities";
	}

	private List<String> generateMonthlyDisplayList(LocalDate subscriptionExpiry, int months) {
		List<String> monthlyList = new ArrayList<>();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd, YYYY");
        
		for(int i=1; i<=months; i++) {
			LocalDate next_month = subscriptionExpiry.plusMonths(i);
			monthlyList.add(next_month.format(formatter));
		}
		return monthlyList;
	}
	
	private List<String> generateYearlyDisplayList(LocalDate subscriptionExpiry, int years) {
		List<String> yearlyList = new ArrayList<>();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd, YYYY");
        
		for(int i=1; i<=years; i++) {
			LocalDate next_year = subscriptionExpiry.plusYears(i);
			yearlyList.add(next_year.format(formatter));
		}
		return yearlyList;
	}

	@PostMapping("/member/{memberId}/payUtilitiesBreakdown")
	public String getUtilityBreakdown(HttpServletRequest request, 
			@PathVariable(name = "memberId") String memberId,
			@RequestParam(name = "subscriptionNewValidity") @NonNull String subscriptionNewValidity) {
		Member member = memberService.getMemberById(memberId);
		
		request.setAttribute("member", member);
		request.setAttribute("chargesBreakdown", "true");
		
		LocalDate from = member.getSubscriptionExpiry();

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd, yyyy"); 
		LocalDate to = LocalDate.parse(subscriptionNewValidity, formatter);
				
		long months_to_charge = ChronoUnit.MONTHS.between(from, to);
		
		request.setAttribute("months", months_to_charge);
		request.setAttribute("water_monthly", feeService.getFeeById("Water").getFeeValue());
		request.setAttribute("trash_monthly", feeService.getFeeById("Trash").getFeeValue());
		request.setAttribute("water_total", feeService.getFeeById("Water").getFeeValue() * months_to_charge);
		request.setAttribute("trash_total", feeService.getFeeById("Trash").getFeeValue() * months_to_charge);
		request.setAttribute("total", (feeService.getFeeById("Trash").getFeeValue() * months_to_charge) + (feeService.getFeeById("Water").getFeeValue() * months_to_charge));
		request.setAttribute("subscriptionNewValidity", subscriptionNewValidity);

		return "payUtilitiesBreakdown";
	}

	@GetMapping("/member/{memberId}/newRequests")
	public String showNewRequests(HttpServletRequest request, @PathVariable(name = "memberId") String memberId) {
		Member member = memberService.getMemberById(memberId);
		
		request.setAttribute("member", member);
		List<Fee> requestsList = feeService.getAllRequestFees();
		request.setAttribute("requestsList", requestsList);
		
		return "newRequests";
	}	

	@GetMapping("/members/download")
	public void exportToPDF(HttpServletResponse response) throws PdfException, DocumentException, IOException {
		response.setContentType("application/pdf");
		String creationTimeMillis_4ID = "" + System.currentTimeMillis() / 1000;

		String headerKey = "Content-Disposition";
		String headerValue = "attachment; filename=members_" + creationTimeMillis_4ID + ".pdf";
		response.setHeader(headerKey, headerValue);

		List<Member> allMembersList = memberService.getAllMembers();

		MembersPDFExporter exporter = new MembersPDFExporter(allMembersList);
		exporter.export(response);
	}

}
