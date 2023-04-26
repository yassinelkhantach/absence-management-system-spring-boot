package com.project.absenceManagementSystem.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.absenceManagementSystem.entities.AbsenceJustification;

@Repository
public interface AbsenceJustificationRepository extends JpaRepository<AbsenceJustification, Long> {

}
