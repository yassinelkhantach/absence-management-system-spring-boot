package com.project.absenceManagementSystem.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.absenceManagementSystem.entities.SessionType;

@Repository
public interface SessionTypeRepository extends JpaRepository<SessionType, Long>{

}
