package com.project.absenceManagementSystem.services;

import java.util.List;
import java.util.Optional;

import com.project.absenceManagementSystem.entities.Student;
import com.project.absenceManagementSystem.entities.Teacher;
import com.project.absenceManagementSystem.entities.User;

public interface UserEntityService {
	Optional<User> getUserByEmail(String email);
	List<Student> getAllStdudents();
	List<Teacher> getAllTeachers();

}
