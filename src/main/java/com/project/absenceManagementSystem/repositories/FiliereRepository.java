package com.project.absenceManagementSystem.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.absenceManagementSystem.entities.Filiere;
@Repository
public interface FiliereRepository extends JpaRepository<Filiere, Long>{

}
