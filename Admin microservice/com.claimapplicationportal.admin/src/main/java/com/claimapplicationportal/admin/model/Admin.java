package com.claimapplicationportal.admin.model;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


@Getter
@Setter
@AllArgsConstructor @NoArgsConstructor
@Entity
public class Admin {

	@GeneratedValue(strategy = GenerationType.AUTO)
	private int userId;
	@Id
	private String emailId; //username
	private String password;
	private String createdBy;
	private String createdOn;
	private String updatedBy;
	private String updatedOn;


	
	
}
