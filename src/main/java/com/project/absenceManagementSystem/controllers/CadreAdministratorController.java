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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.project.absenceManagementSystem.dto.AbscenceDto;
import com.project.absenceManagementSystem.dto.UserRegistrationDto;
import com.project.absenceManagementSystem.entities.Absence;
import com.project.absenceManagementSystem.entities.CadreAdministrator;
import com.project.absenceManagementSystem.entities.Student;
import com.project.absenceManagementSystem.entities.Teacher;
import com.project.absenceManagementSystem.services.AbsenceService;
import com.project.absenceManagementSystem.services.UserEntityService;
import com.project.absenceManagementSystem.services.security.UserService;


@Controller
@RequestMapping("/cadre-administrator")
public class CadreAdministratorController {
	@Autowired
	private UserEntityService userService;
	
	@Autowired
	private AbsenceService absenceService;
	
	@Autowired
	private UserService userRegistrationService;
	
	@ModelAttribute("student") 
	public UserRegistrationDto getStudentRegistrationDTO() {
		return new UserRegistrationDto();
	}
	
	@ModelAttribute("teacher") 
	public UserRegistrationDto getTeacherRegistrationDTO() {
		return new UserRegistrationDto();
	}
	
	@ModelAttribute("userStudent") 
	public Student getStudent() {
		return new Student();
	}
	
	@ModelAttribute("userTeacher") 
	public Teacher getTeacher() {
		return new Teacher();
	}

	@ModelAttribute("userCadreAdministrator") 
	public CadreAdministrator getCadreAdministrator() {
		return new CadreAdministrator();
	}
	
	@ModelAttribute("absence") 
	public AbscenceDto getAbsence() {
		return new AbscenceDto();
	}
	
	@PostMapping("/profile/edit")
	public String editProfile(@ModelAttribute("userCadreAdministrator") CadreAdministrator user,@RequestParam("id") Long id, Authentication auth, RedirectAttributes redirectAttributes) {
		if(userService.updateCadreAdministrator(id, user) != null) {
			redirectAttributes.addFlashAttribute("success","Informations modifées avec succès");
		}else {
			redirectAttributes.addFlashAttribute("failed","Une erreur s'est produite ! veuillez ré-essayer plus tard");
		}
		return "redirect:/profile";	
	}
	
	/*Students management methods*/
	@GetMapping("/students")
	public String students(@RequestParam(name="query",required=false) String query,Model model,Authentication auth){
		model.addAttribute("user",userService.getUserByEmail(auth.getName()).get());
		if(query != null && query != "")
			model.addAttribute("students",userService.searchStudents(query));
		else
			model.addAttribute("students",userService.getAllStdudents());
		model.addAttribute("query",query);
		return "students/students";
	}
	
	@PostMapping("/students/delete")
	public String deleteStudent(@RequestParam(name="id",required=false) Long id,Model model,Authentication auth, RedirectAttributes redirectAttributes){
		model.addAttribute("user",userService.getUserByEmail(auth.getName()).get());
		if(userService.deleteStudent(id) != null)
			redirectAttributes.addFlashAttribute("success","Etudiant supprimé avec succès");
		else
			redirectAttributes.addFlashAttribute("failed","Une erreur s'est produite ! veuillez ré-essayer plus tard");
		return "redirect:/cadre-administrator/students";
	}
	

	@GetMapping("/student/{id}/edit")
	public String editStudent(@PathVariable("id") Long id,Model model,Authentication auth){
		model.addAttribute("user",userService.getUserByEmail(auth.getName()).get());
		model.addAttribute("student",userService.getUserById(id).orElse(null));
		return "students/edit-student";
	}
	
	@GetMapping("/student/{id}/details")
	public String studentDetails(@PathVariable("id") Long id,Model model,Authentication auth){
		model.addAttribute("user",userService.getUserByEmail(auth.getName()).get());
		model.addAttribute("student",userService.getUserById(id).orElse(null));
		return "students/student-details";
	}
	
	@GetMapping("/student/add")
	public String addStudentPage(Model model,Authentication auth){
		model.addAttribute("user",userService.getUserByEmail(auth.getName()).get());
		return "students/add-student";
	}
	
	@PostMapping("/student/add")
	public String addStudent(@ModelAttribute("student") UserRegistrationDto student,Model model,Authentication auth,RedirectAttributes redirectAttributes){
		model.addAttribute("user",userService.getUserByEmail(auth.getName()).get());
		student.setPassword(student.getCne());
		student.setRole("student");
		if(userRegistrationService.save(student) != null)
			redirectAttributes.addFlashAttribute("success","Etudiant ajouté avec succès");
		else
			redirectAttributes.addFlashAttribute("failed","Une erreur s'est produite ! veuillez ré-essayer plus tard");
		return "redirect:/cadre-administrator/student/add";
	}
	
	@PostMapping("/student/{id}/edit")
	public String updateStudent(@ModelAttribute("userStudent") Student student,@PathVariable("id") Long id,Model model,Authentication auth, RedirectAttributes redirectAttributes){
		model.addAttribute("user",userService.getUserByEmail(auth.getName()).get());
		if(userService.updateStudent(id, student) != null) {
			redirectAttributes.addFlashAttribute("success","Informations modifées avec succès");
		}else {
			redirectAttributes.addFlashAttribute("failed","Une erreur s'est produite ! veuillez ré-essayer plus tard");
		}
		return "redirect:/cadre-administrator/student/"+id+"/edit";
	}
	
	
	
	/*Teachers management methods*/
	@GetMapping("/teachers")
	public String teachers(@RequestParam(name="query",required=false) String query,Model model,Authentication auth){
		model.addAttribute("user",userService.getUserByEmail(auth.getName()).get());
		if(query != null && query != "")
			model.addAttribute("teachers",userService.searchTeachers(query));
		else
			model.addAttribute("teachers",userService.getAllTeachers());
		model.addAttribute("query",query);
		return "teachers/teachers";
	}
	
	
	@PostMapping("/teachers/delete")
	public String deleteTeacher(@RequestParam(name="id",required=false) Long id,Model model,Authentication auth, RedirectAttributes redirectAttributes){
		model.addAttribute("user",userService.getUserByEmail(auth.getName()).get());
		if(userService.deleteTeacher(id) != null)
			redirectAttributes.addFlashAttribute("success","Professeur supprimé avec succès");
		else
			redirectAttributes.addFlashAttribute("failed","Une erreur s'est produite ! veuillez ré-essayer plus tard");
		return "redirect:/cadre-administrator/teachers";
	}
	

	@GetMapping("/teacher/{id}/edit")
	public String editTeacher(@PathVariable("id") Long id,Model model,Authentication auth){
		model.addAttribute("user",userService.getUserByEmail(auth.getName()).get());
		model.addAttribute("teacher",userService.getUserById(id).orElse(null));
		return "teachers/edit-teacher";
	}
	
	@GetMapping("/teacher/{id}/details")
	public String teacherDetails(@PathVariable("id") Long id,Model model,Authentication auth){
		model.addAttribute("user",userService.getUserByEmail(auth.getName()).get());
		model.addAttribute("teacher",userService.getUserById(id).orElse(null));
		return "teachers/teacher-details";
	}
	
	@GetMapping("/teacher/add")
	public String addTeacherPage(Model model,Authentication auth){
		model.addAttribute("user",userService.getUserByEmail(auth.getName()).get());
		return "teachers/add-teacher";
	}
	
	@PostMapping("/teacher/add")
	public String addTeacher(@ModelAttribute("teacher") UserRegistrationDto teacher,Model model,Authentication auth,RedirectAttributes redirectAttributes){
		model.addAttribute("user",userService.getUserByEmail(auth.getName()).get());
		teacher.setPassword(teacher.getCin());
		teacher.setRole("teacher");
		if(userRegistrationService.save(teacher) != null)
			redirectAttributes.addFlashAttribute("success","Professeur ajouté avec succès");
		else
			redirectAttributes.addFlashAttribute("failed","Une erreur s'est produite ! veuillez ré-essayer plus tard");
		return "redirect:/cadre-administrator/teacher/add";
	}
	
	@PostMapping("/teacher/{id}/edit")
	public String updateTeacher(@ModelAttribute("userTeacher") Teacher teacher,@PathVariable("id") Long id,Model model,Authentication auth, RedirectAttributes redirectAttributes){
		model.addAttribute("user",userService.getUserByEmail(auth.getName()).get());
		if(userService.updateTeacher(id, teacher) != null) {
			redirectAttributes.addFlashAttribute("success","Informations modifées avec succès");
		}else {
			redirectAttributes.addFlashAttribute("failed","Une erreur s'est produite ! veuillez ré-essayer plus tard");
		}
		return "redirect:/cadre-administrator/teacher/"+id+"/edit";
	}
	
	/*Teachers management methods*/
	@GetMapping("/absences")
	public String absences(@RequestParam(name = "teacher",required = false ) Long id, Model model,Authentication auth){
		model.addAttribute("user",userService.getUserByEmail(auth.getName()).get());
		model.addAttribute("teachers",userService.getAllTeachers());
		if(id != null) {
			model.addAttribute("sessionTypes",userService.getSessionTypes());
			model.addAttribute("teacherChoosen",(Teacher)userService.getUserById(id).orElse(getTeacher()));
		}
		return "absences/absences";
	}
	
	@PostMapping("/absences/add")
	public String addAbsence(@ModelAttribute("absence") AbscenceDto absence,Authentication auth,RedirectAttributes redirectAttributes) throws ParseException{
		Absence abs = new Absence();
		abs.setElement(userService.getElementById(absence.getElement()));
		abs.setRegistration(userService.getRegistrationById(absence.getRegistration()));
		abs.setJustified(false);
		abs.setStart(new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss").parse(absence.getStartString()));
		abs.setTeacher((Teacher)userService.getUserById(absence.getTeacher()).orElse(null));
		abs.setSessionType(userService.getSessionTypeById(absence.getSession()));
		abs.setCreatedAt(new Date());
		if(absenceService.addAbsence(abs) != null)
			redirectAttributes.addFlashAttribute("success","Absence enregistrée avec succès !");
		else
			redirectAttributes.addFlashAttribute("failed","Une erreur s'est produite ! veuillez ré-essayer plus tard");
		
		return "redirect:/cadre-administrator/absences?teacher="+absence.getTeacher();
	}
	
	
}
