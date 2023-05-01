package com.project.absenceManagementSystem.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/cadre-administrator")
public class CadreAdministratorController {

	@GetMapping
	public String home(){
		return "index";
	}
	
}
