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
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
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
	

	@PostMapping("/profile/edit/photo")
	public String uploadImage(Authentication auth, @RequestParam("photo") MultipartFile file, RedirectAttributes redirectAttributes) throws IOException {
		Student student = (Student) userEntityService.getUserByEmail(auth.getName()).get();

        // Create a directory with the name of the student
        String studentDirectoryName = student.getId().toString();
        Path studentDirectoryPath = Paths.get("src/main/resources/static"+fileStorageLocation, studentDirectoryName);
        Files.createDirectories(studentDirectoryPath);     
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
        String currentTime = dateFormat.format(new Date());
        Path filePath = studentDirectoryPath.resolve(currentTime+file.getOriginalFilename());
        userEntityService.updatePicture(student.getId(), fileStorageLocation+studentDirectoryName+"/"+currentTime+file.getOriginalFilename());
        if(Files.write(filePath, file.getBytes()) != null)
        	redirectAttributes.addFlashAttribute("success","Vous avez modifié votre image avec succès !");
        else
        	redirectAttributes.addFlashAttribute("failed","Une erreur s'est produite ! Veuillez ré-essayer plutard");
		return "redirect:/profile/edit";
    }
	
	
}
