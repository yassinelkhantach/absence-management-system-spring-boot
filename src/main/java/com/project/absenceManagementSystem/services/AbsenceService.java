package com.project.absenceManagementSystem.services;

import java.util.List;

import com.project.absenceManagementSystem.entities.Absence;

public interface AbsenceService {
	Absence getAbsenceById(Long id);
	Absence addAbsence(Absence absence);
	Absence updateAbsence(Long id,Absence absence);
	Absence deleteAbsence(Long id);
	List<Absence> getAbsencesByRegistration(Long id);
	List<Absence> getAbsencesByTeacher(Long id);
	List<Absence> getAllAbsences();

}
