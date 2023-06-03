package com.project.absenceManagementSystem.controllers;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.project.absenceManagementSystem.dto.AbscenceDto;
import com.project.absenceManagementSystem.entities.Absence;
import com.project.absenceManagementSystem.entities.Teacher;
import com.project.absenceManagementSystem.services.AbsenceService;
import com.project.absenceManagementSystem.services.UserEntityService;

@Controller
@RequestMapping("/teacher")
public class TeacherController {
	
	@Autowired
	private UserEntityService userEntityService;
	
	@Autowired
	private AbsenceService absenceService;

	
	@ModelAttribute("absence") 
	public AbscenceDto getAbsence() {
		return new AbscenceDto();
	}

	@PostMapping("/profile/edit")
	public String editProfile(@ModelAttribute("user") Teacher user,@RequestParam("id") Long id, Authentication auth, RedirectAttributes redirectAttributes) {
		if(userEntityService.updateTeacher(id, user) != null) {
			redirectAttributes.addFlashAttribute("success","Informations modifées avec succès");
		}else {
			redirectAttributes.addFlashAttribute("failed","Une erreur s'est produite ! veuillez ré-essayer plus tard");
		}
		return "redirect:/profile";	
	}
	

	@GetMapping("/absences")
	public String home(Model model ,Authentication auth) {
		model.addAttribute("user",userEntityService.getUserByEmail(auth.getName()).get());
		model.addAttribute("sessionTypes",userEntityService.getSessionTypes());
		return "teachers/teacher";	
	}
	

	@PostMapping("/absences/add")
	public String addAbsence(@ModelAttribute("absence") AbscenceDto absence,Authentication auth,RedirectAttributes redirectAttributes) throws ParseException{
		Absence abs = new Absence();
		System.err.println(absence);
		abs.setElement(userEntityService.getElementById(absence.getElement()));
		abs.setRegistration(userEntityService.getRegistrationById(absence.getRegistration()));
		abs.setJustified(false);
		abs.setStart(new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss").parse(absence.getStartString()));
		abs.setTeacher((Teacher)userEntityService.getUserById(absence.getTeacher()).orElse(null));
		abs.setSessionType(userEntityService.getSessionTypeById(absence.getSession()));
		abs.setCreatedAt(new Date());
		if(absenceService.addAbsence(abs) != null)
			redirectAttributes.addFlashAttribute("success","Absence enregistrée avec succès !");
		else
			redirectAttributes.addFlashAttribute("failed","Une erreur s'est produite ! veuillez ré-essayer plus tard");
		
		return "redirect:/teacher/absences";
	}
	

}
