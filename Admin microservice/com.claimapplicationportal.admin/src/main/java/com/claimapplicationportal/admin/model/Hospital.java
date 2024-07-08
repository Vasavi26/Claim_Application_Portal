package com.claimapplicationportal.admin.model;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor @NoArgsConstructor
@Entity

public class Hospital {
//	@GeneratedValue(strategy=GenerationType.AUTO)
	@Id
	public String hospitalId;

	private String hospitalName;



}
