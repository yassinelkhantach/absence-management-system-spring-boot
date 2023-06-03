package com.project.absenceManagementSystem.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.absenceManagementSystem.dto.AbscenceDto;
import com.project.absenceManagementSystem.entities.Absence;
import com.project.absenceManagementSystem.entities.Student;
import com.project.absenceManagementSystem.services.AbsenceService;
import com.project.absenceManagementSystem.services.UserEntityService;

@RestController
@RequestMapping("/absences")
public class AbsencesRestController {
	@Autowired
	private AbsenceService absenceService;
	@Autowired
	private UserEntityService useService;
 

	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteAbsence(@PathVariable Long id, Authentication auth) {
	    List<String> authorities = auth.getAuthorities().stream().map(Object::toString).collect(Collectors.toList());
	    if (authorities.contains("ROLE_CADRE_ADMINISTRATOR") || authorities.contains("ROLE_TEACHER")) {
	        // Check if the absence exists
	        Absence absence = absenceService.getAbsenceById(id);
	        if (absence != null) {
	            // Delete the absence
	            absenceService.deleteAbsence(id);
	            return ResponseEntity.ok("Absence supprimée avec succès");
	        } else {
	            return ResponseEntity.notFound().build();
	        }
	    } else {
	        return ResponseEntity.notFound().build();
	    }
	}
    
    @PutMapping("/{id}")
    public ResponseEntity<AbscenceDto> updateAbsence(@PathVariable Long id, @RequestBody AbscenceDto data,Authentication auth) {
    	List<String> authorities = auth.getAuthorities().stream().map(Object::toString).collect(Collectors.toList());
    	if(authorities.contains("ROLE_CADRE_ADMINISTRATOR") || authorities.contains("ROLE_TEACHER")) {
    		Absence absence = absenceService.getAbsenceById(id);
        	absence.setStart(data.getStart());
        	absence.setEnd(data.getEnd());
        	Absence updatedAbsence = absenceService.updateAbsence(id, absence);
            if (updatedAbsence != null) {
                return ResponseEntity.ok(data);
            } else {
                return ResponseEntity.notFound().build();
            }
    	}
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/teacher/{id}")
    public ResponseEntity<List<AbscenceDto>> getAbsencesByTeacher(@PathVariable Long id, Authentication auth) {
    	List<String> authorities = auth.getAuthorities().stream().map(Object::toString).collect(Collectors.toList());
    	if(authorities.contains("ROLE_CADRE_ADMINISTRATOR") || authorities.contains("ROLE_TEACHER")) {
	
	    	List<AbscenceDto> absences = new ArrayList<>();
	        try {
	        	for(Absence absence : absenceService.getAbsencesByTeacher(id)){
	        		AbscenceDto obj = new AbscenceDto();
	            	obj.setId(absence.getId());
	            	obj.setSession(absence.getSessionType().getId());
	            	obj.setJustified(absence.getJustified());
	            	obj.setStart(absence.getStart());
	            	obj.setEnd(absence.getEnd());
	            	obj.setStudent(absence.getRegistration().getStudent().getId());
	            	obj.setStudent_name(absence.getRegistration().getStudent().getFullName());
	            	obj.setElement(absence.getElement().getId());
	            	obj.setElement_label(absence.getElement().getLabel());
	            	obj.setCreatedAt(absence.getCreatedAt());
	            	obj.setTeacher(absence.getTeacher().getId());   
	            	obj.setTeacher_name(absence.getTeacher().getFullName());   
	
	            	absences.add(obj);
	            }
			} catch (Exception e) {
				System.err.println(e.getStackTrace());
			}
	        return ResponseEntity.ok(absences);
    	}else {
            return ResponseEntity.notFound().build();
    	}
    }

    @GetMapping("/student/{id}")
    public ResponseEntity<List<AbscenceDto>> getAbsencesByStudent(@PathVariable Long id) {
        List<AbscenceDto> absences = new ArrayList<>();
    	try {
    		Student student = (Student) useService.getUserById(id).orElse(null); 
            for(Absence absence : absenceService.getAbsencesByRegistration(student.getRegistrations().get(0).getId()))
            {
            	AbscenceDto obj = new AbscenceDto();
            	obj.setId(absence.getId());
            	obj.setJustified(absence.getJustified());
            	obj.setStart(absence.getStart());
            	obj.setEnd(absence.getEnd());
            	obj.setSession(absence.getSessionType().getId());
            	obj.setStudent(absence.getRegistration().getStudent().getId());
            	obj.setStudent_name(absence.getRegistration().getStudent().getFullName());
            	obj.setElement(absence.getElement().getId());
            	obj.setElement_label(absence.getElement().getLabel());
            	obj.setCreatedAt(absence.getCreatedAt());
            	obj.setTeacher(absence.getTeacher().getId());    
            	obj.setTeacher_name(absence.getTeacher().getFullName());   
            	absences.add(obj);
            }
		} catch (Exception e) {
			System.err.println(e.getStackTrace());
		}
        return ResponseEntity.ok(absences);
    }
}
