package com.project.absenceManagementSystem.services.security;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.Optional;
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
import com.project.absenceManagementSystem.entities.Registration;
import com.project.absenceManagementSystem.entities.Role;
import com.project.absenceManagementSystem.entities.Student;
import com.project.absenceManagementSystem.entities.Teacher;
import com.project.absenceManagementSystem.entities.User;
import com.project.absenceManagementSystem.exceptions.DuplicateEmailException;
import com.project.absenceManagementSystem.repositories.AccountRepository;
import com.project.absenceManagementSystem.repositories.UserRepository;


@Service
public class UserServiceImpl implements UserService{

	@Autowired
	private UserRepository userRepository;
	@Autowired
	private AccountRepository accountRepository;

	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	
	@Override
	public User save(UserRegistrationDto registrationDto) {
		if (emailExist(registrationDto.getEmail())) {
            throw new DuplicateEmailException("The email address already exists.");
        }
		Account account = new Account(registrationDto.getEmail(), passwordEncoder.encode(registrationDto.getPassword()), registrationDto.getRole(), false, true, new Date(), null, null);
		User user = null;
		if(account.getRole().toLowerCase().equals("student")) {
			try {
				Registration reg = new Registration(false,new Date(), null);
				user = new Student(registrationDto.getFirstName(),registrationDto.getLastName(),
						registrationDto.getFirstNameAr(),registrationDto.getLastNameAr(),registrationDto.getPhone(),
						registrationDto.getEmail(),null,registrationDto.getCin(),registrationDto.getCne(),
						null,new SimpleDateFormat("yyyy-MM-dd").parse(registrationDto.getBirthdate()),Arrays.asList(new Role("ROLE_STUDENT")),Arrays.asList(reg));
				//save registration of the user
				reg.setStudent((Student)user);
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}else
		if(account.getRole().toLowerCase().equals("teacher")) {
			user = new Teacher(registrationDto.getFirstName(),registrationDto.getLastName(),
					registrationDto.getFirstNameAr(),registrationDto.getLastNameAr(),registrationDto.getPhone(),
					registrationDto.getEmail(),null,registrationDto.getCin(),Arrays.asList(new Role("ROLE_TEACHER")));
		}else
		if(account.getRole().toLowerCase().equals("cadreadministrator")){
			user = new CadreAdministrator(registrationDto.getFirstName(),registrationDto.getLastName(),
					registrationDto.getFirstNameAr(),registrationDto.getLastNameAr(),registrationDto.getPhone(),
					registrationDto.getEmail(),null,registrationDto.getGrade(),Arrays.asList(new Role("ROLE_CADRE_ADMINISTRATOR")));
		}
		user.setAccount(account);
		account.setUser(user);
		user =  userRepository.save(user);
		return user;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
	
		User user = userRepository.findByEmail(username).get();
		if(user == null) {
			throw new UsernameNotFoundException("Invalid username or password.");
		}else
		if(accountRepository.getOne(user.getAccount().getId()) == null) {
			throw new UsernameNotFoundException("Ce compte n'existe pas");
		}
		return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getAccount().getPassword(),  mapRolesToAuthorities(user.getRoles()));		
	}
	
	
	private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles){
		return roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
	}
	
	private boolean emailExist(String email) {
        Optional<User> user = userRepository.findByEmail(email);
        if (!user.isEmpty()) {
            return true;
        }
        return false;
    }

}