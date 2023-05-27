package com.project.absenceManagementSystem.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.project.absenceManagementSystem.services.UserEntityService;

@Controller
@RequestMapping(path = {"/","/admin","/student","/teacher","cadre-administrator"})
public class CommonController {
	@Autowired
	private UserEntityService userService;
	
	@GetMapping
	public String home(Model model ,Authentication auth) {
		model.addAttribute("user",userService.getUserByEmail(auth.getName()).get());
		return "index";	
	}
}
