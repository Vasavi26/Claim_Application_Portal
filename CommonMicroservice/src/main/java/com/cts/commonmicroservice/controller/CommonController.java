package com.cts.commonmicroservice.controller;

import com.cts.commonmicroservice.exception.ClaimException;
import com.cts.commonmicroservice.model.Claim;
import com.cts.commonmicroservice.service.CommonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.FileNotFoundException;
import java.util.List;

@RestController
@CrossOrigin(allowedHeaders = "*",origins = "*")
public class CommonController {
    @Autowired
    private CommonService service;
    @PostMapping("/saveClaim")
    public Claim submitClaim(@RequestBody Claim claimDetails) throws ClaimException {
        return service.submitClaim(claimDetails);

    }

    @GetMapping(value = "/getClaimsByEmailId/{email}")
    public List<Claim> getClaimsByEmail(@PathVariable String email) throws ClaimException {
        return service.getClaimsByEmail(email);
    }

    @GetMapping(value = "/getClaimById/{id}")
    public Claim getClaimById(@PathVariable long id) throws ClaimException {
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
    public String downloadClaim(@PathVariable long claimId) throws FileNotFoundException, ClaimException {
        return service.downloadClaim(claimId);
    }

    @GetMapping(value = "/getClaims")
    public List<Claim> getClaims() throws ClaimException {
        return service.getClaims();
    }
    @GetMapping(value="/searchByEmail/{emailId}")
    public List<Claim> searchClaimByEmailId(@PathVariable String emailId) throws ClaimException {
        return service.searchClaimByEmailId(emailId);
    }

}
