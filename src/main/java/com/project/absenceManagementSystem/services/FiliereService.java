package com.project.absenceManagementSystem.services;

import java.util.List;

import com.project.absenceManagementSystem.entities.Filiere;
import com.project.absenceManagementSystem.entities.Level;

public interface FiliereService {
	public Filiere getFiliereById(Long id);
	public Filiere updateFiliere(Long id, Filiere filiere);
	public Filiere deleteFiliere(Long id);
	public List<Filiere> getAllFilieres();
	public Level getLevelById(Long id);
	public List<Level> getAllLevels();
	public List<com.project.absenceManagementSystem.entities.Module> getAllModules();



}
