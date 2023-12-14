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
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


import com.projects.communityhoa.model.User;
import com.projects.communityhoa.model.UserRole;
import com.projects.communityhoa.service.UserService;

import jakarta.servlet.http.HttpServletRequest;


@Controller
public class HomeController {
	
    @Autowired
    UserService userService;
    
	@GetMapping({"/", "/auth", "/login*"})
	public String redirect2Auth() {
		return "redirect:/login";
	}
	
	@GetMapping("/login")
	public String showLogin() {
		return "login";
	}
	
    @PostMapping("/login")
    public String handleLogin(@RequestParam String username, @RequestParam String password, HttpServletRequest request){

        User user = userService.getUserByUsername(username);

        System.out.println("Handling sign in: "+ user.getUsername());
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

        if(user == null){
        	request.setAttribute("error", "invalid-username");
            return "redirect:/login";
        }
        else{
            if(bCryptPasswordEncoder.matches(password, user.getPassword())){
                request.getSession().setAttribute("user", user);
            	return "redirect:/home";
            }
            else {
            	request.setAttribute("error", "invalid-password");
                return "redirect:/login";
            }
        }
    }

	@GetMapping("/signup")
	public String showSignup() {
	
//		User user = new User();
//		user.setUsername("pragnya");
//        BCryptPasswordEncoder psdEncoder = new BCryptPasswordEncoder();
//
//		user.setPassword(psdEncoder.encode("pwd"));
//		user.setUser_type(UserRole.ADMIN);
//	    userService.save(user);
	    
		return "signup";
	}
	
	@GetMapping("/error")
	public String showErrorPage() {
		return "error";
	}

}
