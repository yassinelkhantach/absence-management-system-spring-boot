package com.project.absenceManagementSystem.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.project.absenceManagementSystem.services.UserEntityService;


@Controller
@RequestMapping("/cadre-administrator")
public class CadreAdministratorController {
	@Autowired
	private UserEntityService userService;
	
	@GetMapping("/students")
	public String students(Model model,Authentication auth){
		model.addAttribute("user",userService.getUserByEmail(auth.getName()).get());
		model.addAttribute("students",userService.getAllStdudents());
		return "students/students";
	}
	
	@GetMapping("/teachers")
	public String teachers(Model model,Authentication auth){
		model.addAttribute("user",userService.getUserByEmail(auth.getName()).get());
		model.addAttribute("teachers",userService.getAllTeachers());
		return "teachers/teachers";
	}
	
	
	
}
