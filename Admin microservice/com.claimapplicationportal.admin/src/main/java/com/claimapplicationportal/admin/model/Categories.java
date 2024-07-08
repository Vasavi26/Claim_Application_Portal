package com.claimapplicationportal.admin.model;

import lombok.*;

import javax.persistence.*;
@Entity
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
public class Categories {
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Id
	private int categoryId;
//	@Column(unique = true)
	private String categoryName;

	private String createdBy;
	private String createdOn;

	private String updatedBy;

	private String updatedOn;
	


}
