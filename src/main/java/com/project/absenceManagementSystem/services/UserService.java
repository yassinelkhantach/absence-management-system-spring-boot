package com.project.absenceManagementSystem.services;


import org.springframework.security.core.userdetails.UserDetailsService;

import com.project.absenceManagementSystem.dto.UserRegistrationDto;
import com.project.absenceManagementSystem.entities.User;

public interface UserService extends UserDetailsService{
	User save(UserRegistrationDto registrationDto);
}