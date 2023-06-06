package com.project.absenceManagementSystem.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.project.absenceManagementSystem.entities.Module;
@Repository
public interface ModuleRepository extends JpaRepository<Module, Long>{
	@Override
    @Transactional(readOnly = true)
    @Query("select e from #{#entityName} e order by level")
    List<Module> findAll();
}
