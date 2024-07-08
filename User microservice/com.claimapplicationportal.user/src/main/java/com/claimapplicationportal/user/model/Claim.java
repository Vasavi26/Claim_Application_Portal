package com.claimapplicationportal.user.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity @Getter @Setter
@AllArgsConstructor @NoArgsConstructor
public class Claim {
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private long claimId;
    private String fullName;
    private int age;
    private String gender;

    @ElementCollection
    private List<String> services;
    private long claimAmount;
//    @Column(unique=true)

    private String email;
    private long templateId;
    private String hospitalId;  //diagnostic id
    private String cityName;
    private String stateName;
    private String address;
    private String status; //claim submitted, pending, approved, rejected
    private String comments;

    private String claimRegisteredDate;
    private String createdBy; //name of the applicant
    private String createdOn; // date of the claim
    private String updatedBy; //name of the applicant
    private String updatedOn; // updated date
}
