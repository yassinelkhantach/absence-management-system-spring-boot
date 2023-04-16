package com.project.absenceManagementSystem.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.absenceManagementSystem.entities.Module;
@Repository
public interface ModuleRepository extends JpaRepository<Module, Long>{
}
