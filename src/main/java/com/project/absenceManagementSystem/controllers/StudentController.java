package com.project.absenceManagementSystem.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.project.absenceManagementSystem.entities.Student;
import com.project.absenceManagementSystem.services.UserEntityService;

@Controller
@RequestMapping(path = {"/","student"})
public class StudentController {
	 @Value("${spring.servlet.multipart.location}")
	 private String fileStorageLocation;
	
	@Autowired
	private UserEntityService userEntityService;
	
	@PostMapping("/profile/edit")
	public String editProfile(@ModelAttribute("user") Student user,@RequestParam("id") Long id, Authentication auth, RedirectAttributes redirectAttributes) {
		if(userEntityService.updateStudent(id, user) != null) {
			redirectAttributes.addFlashAttribute("success","Informations modifées avec succès");
		}else {
			redirectAttributes.addFlashAttribute("failed","Une erreur s'est produite ! veuillez ré-essayer plus tard");
		}
		return "redirect:/profile";	
	}
	
	
}
