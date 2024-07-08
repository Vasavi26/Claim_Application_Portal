package com.claimapplicationportal.admin.repository;

import com.claimapplicationportal.admin.model.ServiceDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ServiceRepo extends JpaRepository <ServiceDetails,Integer>{
}
