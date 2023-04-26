package com.project.absenceManagementSystem.entities;

import java.util.Date;

import jakarta.persistence.CascadeType;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;

@Entity
@DiscriminatorValue(value = "CReq")
public class ClaimRequest extends Claim {

	@OneToOne(cascade = CascadeType.ALL,mappedBy = "claimRequest")
	private ClaimResponse claimResponse;
	
	public ClaimRequest(String subject, String message, Date createdAt, Date deletedAt) {
		super(subject, message, createdAt, deletedAt);
	}

}
