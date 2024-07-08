package com.claimapplicationportal.admin.model;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor @NoArgsConstructor
public class Template{
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Id
	private int templateId;
	@Column(unique = true)
	private String templateName;


//	private String serviceName;
//	@OneToMany(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
	@ElementCollection
	private List<String> services;
	private long amountAllocated;

	private String createdBy;

	private String createdOn;

	private String updatedBy;

	private String updatedOn;

}
