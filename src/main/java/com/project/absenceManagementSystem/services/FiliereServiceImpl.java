package com.project.absenceManagementSystem.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.absenceManagementSystem.entities.Filiere;
import com.project.absenceManagementSystem.entities.Level;
import com.project.absenceManagementSystem.repositories.FiliereRepository;
import com.project.absenceManagementSystem.repositories.LevelRepository;
import com.project.absenceManagementSystem.repositories.ModuleRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class FiliereServiceImpl implements FiliereService{
	@Autowired
	private FiliereRepository filiereRepository;
	@Autowired
	private LevelRepository levelRepository;
	@Autowired
	private ModuleRepository moduleRepository;
	
	@Override
	public Filiere getFiliereById(Long id) {
		return filiereRepository.findById(id).orElse(null);
	}

	@Override
	public Filiere updateFiliere(Long id, Filiere filiere) {
		filiere.setId(id);
		filiereRepository.save(filiere);
		return filiereRepository.findById(id).orElse(null);
	}

	@Override
	public Filiere deleteFiliere(Long id) {
		Filiere filiere = filiereRepository.findById(id).orElse(null);
		filiereRepository.deleteById(id);
		return filiere;
	}

	@Override
	public List<Filiere> getAllFilieres() {
		return filiereRepository.findAll();
	}

	@Override
	public Level getLevelById(Long id) {
		return levelRepository.findById(id).orElse(null);
	}

	@Override
	public List<Level> getAllLevels() {
		return levelRepository.findAll();
	}


	@Override
	public List<com.project.absenceManagementSystem.entities.Module> getAllModules() {
		return moduleRepository.findAll();
	}

}
