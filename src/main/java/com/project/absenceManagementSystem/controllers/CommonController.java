package com.project.absenceManagementSystem.controllers;


import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.project.absenceManagementSystem.entities.User;
import com.project.absenceManagementSystem.services.AbsenceService;
import com.project.absenceManagementSystem.services.FiliereService;
import com.project.absenceManagementSystem.services.UserEntityService;

@Controller
@RequestMapping(path = {"/","/admin","/student","/teacher","cadre-administrator"})
public class CommonController {

	@Autowired
	private UserEntityService userService;

	@Autowired
	private FiliereService filiereService;

	@Autowired
	private AbsenceService absenceService;
	
	@Value("${spring.servlet.multipart.location}")
	private String fileStorageLocation;
	
	
	@GetMapping
	public String home(Model model ,Authentication auth) {
		if (auth == null || !auth.isAuthenticated()){
			return "login";
		}
		model.addAttribute("user",userService.getUserByEmail(auth.getName()).get());
		/*for statistics*/
		model.addAttribute("students",userService.getAllStdudents());
		model.addAttribute("teachers",userService.getAllTeachers());
		model.addAttribute("absences",absenceService.getAllAbsences());

		model.addAttribute("filieres",filiereService.getAllFilieres());
		model.addAttribute("currentPage","dashboard");
		return "index";	
	}
	
	@GetMapping("/profile")
	public String profile(Model model ,Authentication auth) {
		model.addAttribute("user",userService.getUserByEmail(auth.getName()).get());
		model.addAttribute("currentPage","dashboard");
		return "profile/profile";	
	}
	
	@GetMapping("/profile/edit")
	public String editProfilePage(Model model ,Authentication auth) {
		model.addAttribute("user",userService.getUserByEmail(auth.getName()).get());
		model.addAttribute("currentPage","dashboard");
		return "profile/edit-profile";	
	}
	

	@PostMapping("/profile/edit/photo")
	public String uploadImage(Authentication auth, @RequestParam("photo") MultipartFile file, RedirectAttributes redirectAttributes) throws IOException {
		User user = userService.getUserByEmail(auth.getName()).get();

        // Create a directory with the name of the student
        String studentDirectoryName = user.getId().toString();
        Path studentDirectoryPath = Paths.get("src/main/resources/static"+fileStorageLocation, studentDirectoryName);
        Files.createDirectories(studentDirectoryPath);     
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
        String currentTime = dateFormat.format(new Date());
        Path filePath = studentDirectoryPath.resolve(currentTime+file.getOriginalFilename());
        userService.updatePicture(user.getId(), fileStorageLocation+studentDirectoryName+"/"+currentTime+file.getOriginalFilename());
        if(Files.write(filePath, file.getBytes()) != null)
        	redirectAttributes.addFlashAttribute("success","Vous avez modifié votre image avec succès !");
        else
        	redirectAttributes.addFlashAttribute("failed","Une erreur s'est produite ! Veuillez ré-essayer plutard");
		return "redirect:/profile/edit";
    }
	
}
