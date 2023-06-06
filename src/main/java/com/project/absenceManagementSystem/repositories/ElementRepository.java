package com.project.absenceManagementSystem.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import com.project.absenceManagementSystem.entities.Element;

public interface ElementRepository extends JpaRepository<Element, Long>{
	@Override
    @Transactional(readOnly = true)
    @Query("select e from #{#entityName} e order by module")
    List<Element> findAll();
}
