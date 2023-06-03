package com.project.absenceManagementSystem.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.absenceManagementSystem.entities.Registration;

public interface RegistrationRepository extends JpaRepository<Registration, Long>{

}
