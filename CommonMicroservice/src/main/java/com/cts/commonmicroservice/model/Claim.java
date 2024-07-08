package com.cts.commonmicroservice.model;

import lombok.*;


import java.util.List;

@Getter
@Setter
@AllArgsConstructor @NoArgsConstructor
public class Claim {
    private long claimId;
    private String fullName;
    private int age;
    private String gender;

    private List<String> services;
    private long claimAmount;
//    @Column(unique=true)

    private String email;
    private long templateId;
    private String hospitalId;  //diagnostic id
    private String cityName;
    private String stateName;
    private String address;

    private String status;

    private String comments;

    private String claimRegisteredDate;
    private String createdBy; //name of the applicant
    private String createdOn; // date of the claim
    private String updatedBy; //name of the applicant
    private String updatedOn; // updated date
}
