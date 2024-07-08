package com.claimapplicationportal.user.repository;

import com.claimapplicationportal.user.model.Claim;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface ClaimRepo extends JpaRepository<Claim,Long> {
    List<Claim> findByEmail(String email);
    @Query("from Claim C where C.email like %:email%")
    List<Claim> searchClaimByEmailId(String email);
}
