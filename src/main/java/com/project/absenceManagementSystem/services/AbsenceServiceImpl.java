package com.project.absenceManagementSystem.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.absenceManagementSystem.entities.Absence;
import com.project.absenceManagementSystem.repositories.AbsenceRepository;

@Service
public class AbsenceServiceImpl implements AbsenceService{
	
	@Autowired
	private AbsenceRepository absenceRepository;
	
	
	@Override
	public Absence addAbsence(Absence absence) {
		return absenceRepository.save(absence);
	}

	@Override
	public Absence updateAbsence(Long id, Absence absence) {
		absence.setId(id);
		return absenceRepository.save(absence);
	}

	@Override
	public Absence deleteAbsence(Long id) {
		Absence absence = absenceRepository.findById(id).orElse(null);
		absenceRepository.delete(absence);
		return absence;
	}

	@Override
	public List<Absence> getAbsencesByRegistration(Long id) {
		return absenceRepository.getAbsencesByRegistration(id);
	}

	@Override
	public List<Absence> getAbsencesByTeacher(Long id) {
		return absenceRepository.getAbsencesByTeacher(id);
	}

	@Override
	public Absence getAbsenceById(Long id) {
		return absenceRepository.findById(id).orElse(null);
	}

}
