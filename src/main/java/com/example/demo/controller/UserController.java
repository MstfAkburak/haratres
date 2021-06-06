package com.example.demo.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.entity.User;
import com.example.demo.service.UserService;

@Controller
@RequestMapping("")
public class UserController {

	private UserService userService;

	public UserController(UserService theUserService) {
		userService = theUserService;
	}

	@GetMapping("/login")
	public String loginUser(Model theModel) {

		User theUser = new User();

		theModel.addAttribute("user", theUser);
		return "LoginForm";
	}

	@PostMapping("/login")
	public String loginUser(@ModelAttribute User user, @RequestParam String name) {
		
		if (user == null) {
			return "404";
		}
		if(user.getUsername().equals("admin") && user.getPassword().equals("admin")) {
			return "redirect:/tasks/adminlist?name=admin";
		}
		
		if (userService.findByUsername(user.getUsername()).getPassword().equals(user.getPassword())) {
			
			return "redirect:/tasks/list?name=" + user.getUsername() ;
		} else {
			return "redirect:/login";
		}
		}
	

	@GetMapping("/save")
	public String registerUser(Model theModel) {
		User theUser = new User();

		theModel.addAttribute("user", theUser);
		return "userForm";
	}

	@PostMapping("/save")
	public String registerUser(@ModelAttribute User user) {

		if (userService.findByUsername(user.getUsername()) == null) {
			userService.save(user);
			return "redirect:/login";
		} else {
			return "redirect:/save";
		}

	}

	@GetMapping("/list")
	public String listUsers(Model theModel) {
		List<User> theUsers = userService.findAll();
		theModel.addAttribute("users", theUsers);
		return "list";
	}

	/*
	 * @GetMapping("/save") public String saveUser1(Model theModel) {
	 * 
	 * User theUser = new User();
	 * 
	 * theModel.addAttribute("user", theUser);
	 * 
	 * return "userForm"; }
	 * 
	 * @PostMapping("/save") public String saveUser(@ModelAttribute User user) {
	 * 
	 * if (userService.findByUsername(user.getUsername()) == null) {
	 * userService.save(user); return "redirect:/users/list"; } else { return
	 * "redirect:/users/save"; }
	 * 
	 * }
	 */

}