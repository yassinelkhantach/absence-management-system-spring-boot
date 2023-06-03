package com.project.absenceManagementSystem.services;

import java.util.List;
import java.util.Optional;
import com.project.absenceManagementSystem.entities.CadreAdministrator;
import com.project.absenceManagementSystem.entities.Element;
import com.project.absenceManagementSystem.entities.Registration;
import com.project.absenceManagementSystem.entities.SessionType;
import com.project.absenceManagementSystem.entities.Student;
import com.project.absenceManagementSystem.entities.Teacher;
import com.project.absenceManagementSystem.entities.User;

public interface UserEntityService {
	Optional<User> getUserByEmail(String email);
	Optional<User> getUserById(Long id);
	List<Student> getAllStdudents();
	List<Teacher> getAllTeachers();
	List<Student> searchStudents(String query);
	List<Teacher> searchTeachers(String query);
	Student updateStudent(Long id, Student student);
	Teacher updateTeacher(Long id, Teacher teacher);
	CadreAdministrator updateCadreAdministrator(Long id, CadreAdministrator cadreAdministrator);
	Student updatePicture(Long id, String picture);
	Student deleteStudent(Long id);
	Teacher deleteTeacher(Long id);
	Student addStudent(Student student);
	List<SessionType> getSessionTypes();
	SessionType getSessionTypeById(Long id);
	Element getElementById(Long id);
	Registration getRegistrationById(Long id);





}
