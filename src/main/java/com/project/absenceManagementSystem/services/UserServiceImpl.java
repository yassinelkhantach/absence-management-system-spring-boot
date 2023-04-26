package com.project.absenceManagementSystem.services;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import com.project.absenceManagementSystem.dto.UserRegistrationDto;
import com.project.absenceManagementSystem.entities.Account;
import com.project.absenceManagementSystem.entities.CadreAdministrator;
import com.project.absenceManagementSystem.entities.Role;
import com.project.absenceManagementSystem.entities.Student;
import com.project.absenceManagementSystem.entities.Teacher;
import com.project.absenceManagementSystem.entities.User;
import com.project.absenceManagementSystem.repositories.UserRepository;



@Service
public class UserServiceImpl implements UserService{

	@Autowired
	private UserRepository userRepository;
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	
	@Override
	public User save(UserRegistrationDto registrationDto) {
		Account account = new Account(registrationDto.getEmail(), passwordEncoder.encode(registrationDto.getPassword()), registrationDto.getRole(), false, true, new Date(), null, null);
		User user = null;
		if(account.getRole().toLowerCase().equals("student")) {
			try {
				user = new Student(registrationDto.getFirstName(),registrationDto.getLastName(),
						registrationDto.getFirstNameAr(),registrationDto.getLastNameAr(),registrationDto.getPhone(),
						registrationDto.getEmail(),null,registrationDto.getCin(),registrationDto.getCne(),
						null,new SimpleDateFormat("yyyy-MM-dd").parse(registrationDto.getBirthdate()),Arrays.asList(new Role("étudiant")));
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}else
		if(account.getRole().toLowerCase().equals("teacher")) {
			user = new Teacher(registrationDto.getFirstName(),registrationDto.getLastName(),
					registrationDto.getFirstNameAr(),registrationDto.getLastNameAr(),registrationDto.getPhone(),
					registrationDto.getEmail(),null,registrationDto.getCin(),Arrays.asList(new Role("professeur")));
		}else
		if(account.getRole().toLowerCase().equals("cadreadministrator")){
			user = new CadreAdministrator(registrationDto.getFirstName(),registrationDto.getLastName(),
					registrationDto.getFirstNameAr(),registrationDto.getLastNameAr(),registrationDto.getPhone(),
					registrationDto.getEmail(),null,registrationDto.getGrade(),Arrays.asList(new Role("cadre administrateur")));
		}
		user.setAccount(account);
		account.setUser(user);
		return userRepository.save(user);
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
	
		User user = userRepository.findByEmail(username);
		if(user == null) {
			throw new UsernameNotFoundException("Invalid username or password.");
		}
		return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getAccount().getPassword(),  mapRolesToAuthorities(user.getRoles()));		
	}
	
	
	private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles){
		return roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
	}

}