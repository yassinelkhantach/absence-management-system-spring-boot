package com.project.absenceManagementSystem.entities;

import java.util.Date;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;

@Entity
@DiscriminatorValue(value = "CRes")
public class ClaimResponse extends Claim{

	@OneToOne
	@JoinColumn(name = "claim_request_id",referencedColumnName = "id")
	private ClaimRequest claimRequest;
	
	public ClaimResponse(String subject, String message, Date createdAt, Date deletedAt) {
		super(subject, message, createdAt, deletedAt);
	}

}
