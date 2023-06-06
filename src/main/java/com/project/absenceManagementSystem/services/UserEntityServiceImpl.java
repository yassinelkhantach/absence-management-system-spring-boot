package com.project.absenceManagementSystem.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.project.absenceManagementSystem.entities.CadreAdministrator;
import com.project.absenceManagementSystem.entities.Element;
import com.project.absenceManagementSystem.entities.Registration;
import com.project.absenceManagementSystem.entities.SessionType;
import com.project.absenceManagementSystem.entities.Student;
import com.project.absenceManagementSystem.entities.Teacher;
import com.project.absenceManagementSystem.entities.User;
import com.project.absenceManagementSystem.repositories.ElementRepository;
import com.project.absenceManagementSystem.repositories.RegistrationRepository;
import com.project.absenceManagementSystem.repositories.SessionTypeRepository;
import com.project.absenceManagementSystem.repositories.UserRepository;

@Service
public class UserEntityServiceImpl implements UserEntityService {
	
	private UserRepository userRepository;
	private RegistrationRepository registrationRepository;
	private SessionTypeRepository sessionTypeRepository;
	private ElementRepository elementRepository;

	public UserEntityServiceImpl(UserRepository userRepository,RegistrationRepository registrationRepository,SessionTypeRepository sessionTypeRepository,ElementRepository elementRepository) {
		this.userRepository = userRepository;
		this.registrationRepository = registrationRepository;
		this.sessionTypeRepository = sessionTypeRepository;
		this.elementRepository = elementRepository;
	}
	
	@Override
	public Optional<User> getUserById(Long id) {
		return userRepository.findById(id);
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
	
	@Override
	public List<Teacher> getTrashedTeachers() {
		return userRepository.findOnlyTrashedTeachers();
	}

	@Override
	public List<Student> getTrashedStudents() {
		return userRepository.findOnlyTrashedStudents();
	}

	@Override
	public User updatePicture(Long id, String picture) {
		User user = userRepository.findById(id).get();
		user.setPicture(picture);
		return userRepository.save(user);
	}
	
	@Override
	public Student updateStudent(Long id, Student student) {
		student.setId(id);
		Student user = (Student) userRepository.findById(id).get();
		student.setPicture(user.getPicture());
		Registration registration = student.getRegistrations().get(0);
		registration.setId(user.getRegistrations().get(0).getId());
		registration.setStudent(student);
		registration.setCreatedAt(user.getRegistrations().get(0).getCreatedAt());
		registrationRepository.save(registration);
		
		return userRepository.save(student);
	}

	@Override
	public Teacher updateTeacher(Long id, Teacher teacher) {
		teacher.setId(id);
		Teacher user = (Teacher) userRepository.findById(id).get();
		teacher.setPicture(user.getPicture());
		return userRepository.save(teacher);
	}

	@Override
	public CadreAdministrator updateCadreAdministrator(Long id, CadreAdministrator cadreAdministrator) {
		cadreAdministrator.setId(id);
		return userRepository.save(cadreAdministrator);
	}

	@Override
	public List<Student> searchStudents(String query) {
		return userRepository.searchStudents("%"+query+"%");
	}

	@Override
	public List<Teacher> searchTeachers(String query) {
		return userRepository.searchTeachers("%"+query+"%");
	}

	@Override
	public List<Student> searchOnlyTrashedStudents(String query) {
		return userRepository.searchOnlyTrashedStudents("%"+query+"%");
	}

	@Override
	public List<Teacher> searchOnlyTrashedTeachers(String query) {
		return userRepository.searchOnlyTrashedTeachers("%"+query+"%");
	}
	
	@Override
	public Student deleteStudent(Long id) {
		Student user = (Student) userRepository.findById(id).orElse(null);
		userRepository.deleteById(id);
		return user;
	}

	@Override
	public Teacher deleteTeacher(Long id) {
		Teacher user = (Teacher) userRepository.findById(id).orElse(null);
		userRepository.deleteById(id);
		return user;
	}

	@Override
	public Student addStudent(Student student) {
		return userRepository.save(student);
	}

	@Override
	public List<SessionType> getSessionTypes() {
		return sessionTypeRepository.findAll();
	}

	@Override
	public Element getElementById(Long id) {
		return elementRepository.findById(id).orElse(null);
	}

	@Override
	public Registration getRegistrationById(Long id) {
		return registrationRepository.findById(id).orElse(null);
	}

	@Override
	public SessionType getSessionTypeById(Long id) {
		return sessionTypeRepository.findById(id).orElse(null);
	}

	@Override
	public Boolean restoreUser(Long id) {
		if(userRepository.restore(id) != null)
			return true;
		return false;
	}

	@Override
	public Element updateElement(Long id, Element element) {
		element.setId(id);
		return elementRepository.save(element);
	}	



}
