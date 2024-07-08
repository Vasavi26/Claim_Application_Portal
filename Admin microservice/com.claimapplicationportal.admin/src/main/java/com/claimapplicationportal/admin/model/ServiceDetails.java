package com.claimapplicationportal.admin.model;

import lombok.*;

import java.util.List;

import javax.persistence.*;
@Entity
@Getter
@Setter @AllArgsConstructor @NoArgsConstructor

public class ServiceDetails {
	@Id  @GeneratedValue(strategy=GenerationType.IDENTITY)
	private int serviceId;
	@Column(unique = true)
	private String serviceName;

	private long claimAmount;
	private String createdBy;
	private String createdOn;
	private String updatedBy;
	private String updatedOn;

	@OneToMany(fetch = FetchType.LAZY,cascade = CascadeType.ALL)

	private List<Categories> categories;

	
}