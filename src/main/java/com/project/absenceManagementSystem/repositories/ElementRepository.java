package com.project.absenceManagementSystem.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.absenceManagementSystem.entities.Element;

public interface ElementRepository extends JpaRepository<Element, Long>{

}
