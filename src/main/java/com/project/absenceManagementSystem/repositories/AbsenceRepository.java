package com.project.absenceManagementSystem.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.absenceManagementSystem.entities.Absence;

@Repository
public interface AbsenceRepository extends JpaRepository<Absence, Long>{

}
