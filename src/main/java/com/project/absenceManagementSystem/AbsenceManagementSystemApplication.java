package com.project.absenceManagementSystem;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.Transactional;
import lombok.AllArgsConstructor;

@SpringBootApplication
@Transactional
@AllArgsConstructor
public class AbsenceManagementSystemApplication {

	
	public static void main(String[] args) {
		SpringApplication.run(AbsenceManagementSystemApplication.class, args);
	}



}
