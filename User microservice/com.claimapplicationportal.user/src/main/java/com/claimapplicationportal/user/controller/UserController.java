package com.claimapplicationportal.user.controller;

import com.claimapplicationportal.user.exception.ClaimException;
import com.claimapplicationportal.user.model.Claim;
import com.claimapplicationportal.user.service.Userservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@CrossOrigin(origins="http://localhost:4200",allowedHeaders ="*")
public class UserController {

    @Autowired
    private Userservice service;

    @PostMapping("/saveClaim")
    public Claim submitClaim(@RequestBody Claim claimDetails) throws ClaimException {
        return service.submitClaim(claimDetails);

    }

    @GetMapping(value = "/getClaimsByEmailId/{email}")
    public List<Claim> getClaimsByEmail(@PathVariable String email) throws ClaimException {
        return service.getClaimsByEmail(email);
    }

    @GetMapping(value = "/getClaimById/{id}")
    public Claim getClaimById(@PathVariable long id)
    {
        return service.getClaimById(id);
    }

    @PutMapping(value = "/updateClaim")
    public Claim updateClaim(@RequestBody Claim updatingDetails) throws ClaimException {
        return service.updateClaim(updatingDetails);
    }

    @DeleteMapping(value="/deleteClaim/{claimId}")
    public String deleteClaim(@PathVariable long claimId) throws ClaimException {
        return service.deleteClaim(claimId);
    }

    @GetMapping(value = "/downloadClaim/{claimId}")
    public String downloadClaim(@PathVariable long claimId) {
        return service.downloadClaim(claimId);
    }

    @GetMapping(value = "/getClaims")
    public List<Claim> getClaims() throws ClaimException {
        return service.getClaims();
    }
    @GetMapping(value="/searchByEmail/{emailId}")
    public List<Claim> searchClaimByEmailId(@PathVariable String emailId) {
        return service.searchClaimByEmailId(emailId);
    }

}
