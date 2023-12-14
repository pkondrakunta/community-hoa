/**
 * Home & Authentication controller for the Community HOA application. 
 * User authentication and redirecting into application.
 * 
 * @author Pragnya Kondrakunta
 */

package com.projects.communityhoa.controller;

import java.util.List;

//import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.projects.communityhoa.model.Fee;
import com.projects.communityhoa.model.Member;
import com.projects.communityhoa.model.User;
import com.projects.communityhoa.model.UserRole;
import com.projects.communityhoa.service.FeeService;
import com.projects.communityhoa.service.UserService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.NotNull;

@Controller
public class HomeController {

	@Autowired
	UserService userService;
	
	@Autowired
	private FeeService feeService;

	@GetMapping({ "/", "/auth", "/login*" })
	public String redirectToLogin() {
		return "redirect:/login";
	}

	@GetMapping("/login")
	public String showLogin() {
		return "login";
	}
	
	@GetMapping("/home")
	public String redirectToSearch() {
		return "redirect:/searchMember";
	}

	@PostMapping("/login")
	public String handleLogin(@RequestParam(name = "username") String username,
			@RequestParam(name = "password") String password, HttpServletRequest request) {

		User user = userService.getUserByUsername(username);
		BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

		if (user == null) {
			request.setAttribute("error", "invalid-username");
			return "/login";
		} else {
			if (bCryptPasswordEncoder.matches(password, user.getPassword())) {
				request.getSession().setAttribute("user", user);
				return "redirect:/home";
			} else {
				request.setAttribute("error", "invalid-password");
				return "/login";
			}
		}
	}

	@GetMapping("/signup")
	public String showSignup() {
		return "signup";
	}

	@PostMapping("/signup")
	public String handleSignup(HttpServletRequest request, @RequestParam(name = "memberID") String memberId,
			@RequestParam(name = "uname", required = true) String username,
			@RequestParam(name = "psw", required = true) @Size(min = 5, max = 15, message = "Password must be between 5 and 15 characters") String psw) {
		User user = new User();
		user.setUsername(username);
		user.setMemberID(memberId);
		BCryptPasswordEncoder psdEncoder = new BCryptPasswordEncoder();
		user.setPassword(psdEncoder.encode(psw));
		user.setRole(UserRole.MEMBER);
		userService.save(user);
		request.setAttribute("signup", "true");

		return "redirect:/login";
	}

	@GetMapping("/adminSignup")
	public String showAdminSignup() {
		return "adminSignup";
	}

	@PostMapping("/adminSignup")
	public String handleAdminSignup(@RequestParam(name = "username", required = true) String username,
			@RequestParam(name = "psw", required = true) String psw) {

		User user = new User();
		user.setUsername(username);
		BCryptPasswordEncoder psdEncoder = new BCryptPasswordEncoder();

		user.setPassword(psdEncoder.encode(psw));
		user.setRole(UserRole.ADMIN);
		userService.save(user);

		return "redirect:/logout";
	}

	@GetMapping("/logout")
	public String handleLogout(HttpServletRequest request) {

		request.getSession().setAttribute("user", null);
		request.setAttribute("signup", true);
		return "redirect:/login";
	}

	@GetMapping("/users")
	public String handleGetUsers(HttpServletRequest request) {
		List<User> allUsersList = userService.getAllUsers();
		request.setAttribute("userList", allUsersList);
		return "users";
	}
	
	@GetMapping("/allFees")
	public String handleGetFees(HttpServletRequest request) {
		List<Fee> allFeesList = feeService.getAllFees();
		request.setAttribute("feeList", allFeesList);
		return "fees";
	}
	
	@GetMapping("/error")
	public String showErrorPage() {
		return "error";
	}

}
