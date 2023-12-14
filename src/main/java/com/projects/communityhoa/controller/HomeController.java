/**
 * Home & Authentication controller for the Community HOA application. 
 * User authentication and redirecting into application.
 * 
 * @author Pragnya Kondrakunta
 */

package com.projects.communityhoa.controller;

//import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.projects.communityhoa.model.User;
import com.projects.communityhoa.model.UserRole;
import com.projects.communityhoa.service.UserService;

import jakarta.servlet.http.HttpServletRequest;

@Controller
public class HomeController {

	@Autowired
	UserService userService;

	@GetMapping({ "/", "/auth", "/login*" })
	public String redirect2Auth() {
		return "redirect:/login";
	}

	@GetMapping("/login")
	public String showLogin() {
		return "login";
	}

	@PostMapping("/login")
	public String handleLogin(@RequestParam String username, @RequestParam String password,
			HttpServletRequest request) {

		User user = userService.getUserByUsername(username);
		BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

		if (user == null) {
			request.setAttribute("error", "invalid-username");
			return "redirect:/login";
		} else {
			if (bCryptPasswordEncoder.matches(password, user.getPassword())) {
				request.getSession().setAttribute("user", user);
				return "redirect:/home";
			} else {
				request.setAttribute("error", "invalid-password");
				return "redirect:/login";
			}
		}
	}

	@GetMapping("/signup")
	public String showSignup() {
		return "signup";
	}

	@GetMapping("/signup")
	public String handleSignup(@RequestParam(name = "memberID") @NonNull String memberId,
			@RequestParam(name = "uname") @NonNull String username, @RequestParam(name = "psw") @NonNull String psw,
			@RequestParam(name = "con-psw") @NonNull String con_psw) {

		User user = new User();
		user.setUsername(username);
		user.setMemberID(memberId);
		BCryptPasswordEncoder psdEncoder = new BCryptPasswordEncoder();
		user.setPassword(psdEncoder.encode(psw));
		user.setUser_type(UserRole.MEMBER);
		userService.save(user);

		return "redirect:/login";
	}

	@GetMapping("/adminSignup")
	public String showAdminSignup() {
		
		return "adminSignup";
	}
	@GetMapping("/adminSignup")
	public String handleAdminSignup(@RequestParam(name = "uname") @NonNull String username,
			@RequestParam(name = "psw") @NonNull String psw, @RequestParam(name = "con-psw") @NonNull String con_psw) {

		User user = new User();
		user.setUsername(username);
		BCryptPasswordEncoder psdEncoder = new BCryptPasswordEncoder();

		user.setPassword(psdEncoder.encode(psw));
		user.setUser_type(UserRole.ADMIN);
		userService.save(user);

		return "redirect:/login";
	}

	@GetMapping("/error")
	public String showErrorPage() {
		return "error";
	}

}
