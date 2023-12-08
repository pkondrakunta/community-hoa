/**
 * Home & Authentication controller for the Community HOA application. 
 * User authentication and redirecting into application.
 * 
 * @author Pragnya Kondrakunta
 */

package com.projects.communityhoa.controller;

//import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.projects.communityhoa.model.User;
import com.projects.communityhoa.service.UserService;

import jakarta.servlet.http.HttpServletRequest;


@Controller
public class HomeController {
	
    @Autowired
    UserService userService;
    
	@GetMapping({"/", "/auth"})
	public String redirect2Auth() {
		return "redirect:/auth/login";
	}
	
	@GetMapping("/auth/login")
	public String showLogin() {
		return "login";
	}
	
    @PostMapping("auth/login")
    public String handleLogin(@RequestParam String username, @RequestParam String password, HttpServletRequest request){

        User user = userService.getUserByUsername(username);

        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

        if(user == null){
        	request.setAttribute("error", "invalid-username");
            return "redirect:/auth/login";
        }
        else{
            if(bCryptPasswordEncoder.matches(password, user.getPassword())){
                request.getSession().setAttribute("user", user);
            	return "redirect:/home";
            }
            else {
            	request.setAttribute("error", "invalid-password");
                return "redirect:/auth/login";
            }
        }
    }

	@GetMapping("/auth/signup")
	public String showSignup() {
		return "signup";
	}
}
