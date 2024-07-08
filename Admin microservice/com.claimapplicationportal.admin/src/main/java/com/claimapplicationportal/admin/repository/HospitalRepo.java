package com.claimapplicationportal.admin.repository;

import com.claimapplicationportal.admin.model.Hospital;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HospitalRepo extends JpaRepository<Hospital,String> {
}
