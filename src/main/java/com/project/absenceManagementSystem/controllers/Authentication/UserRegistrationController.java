package com.project.absenceManagementSystem.controllers.Authentication;


import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.project.absenceManagementSystem.dto.UserRegistrationDto;
import com.project.absenceManagementSystem.services.security.UserService;


@Controller
@RequestMapping("/registration")
public class UserRegistrationController {

	private UserService userService;

	public UserRegistrationController(UserService userService) {
		super();
		this.userService = userService;
	}
	
	@ModelAttribute("user")
    public UserRegistrationDto userRegistrationDto() {
        return new UserRegistrationDto();
    }
	
	@GetMapping
	public String showRegistrationForm(Authentication authentication) {
		if (authentication != null && authentication.isAuthenticated()) {
			return "redirect:/";
		}
		return "registration";
	}
	
	@PostMapping
	public String registerUserAccount(@ModelAttribute("user") UserRegistrationDto registrationDto, RedirectAttributes redirectAttributes) {
		if(userService.save(registrationDto) != null)
			redirectAttributes.addFlashAttribute("successMessage", "You've successfully registered !");
		else
			redirectAttributes.addFlashAttribute("failedMessage", "An error occured ! Pleease try again");
		return "redirect:/registration";
	}
}