package com.project.absenceManagementSystem.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.project.absenceManagementSystem.entities.Student;
import com.project.absenceManagementSystem.entities.Teacher;
import com.project.absenceManagementSystem.entities.User;
import com.project.absenceManagementSystem.repositories.UserRepository;

@Service
public class UserEntityServiceImpl implements UserEntityService {
	
	private UserRepository userRepository;
	
	public UserEntityServiceImpl(UserRepository userRepository) {
		this.userRepository = userRepository;
	}
	
	@Override
	public Optional<User> getUserByEmail(String email) {
		return userRepository.findByEmail(email);		
	}

	@Override
	public List<Student> getAllStdudents() {
		return userRepository.findAllStudents();
	}

	@Override
	public List<Teacher> getAllTeachers() {
		return userRepository.findAllTeachers();
	}

}
