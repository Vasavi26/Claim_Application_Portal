package com.cts.commonmicroservice.feignclient;

import com.cts.commonmicroservice.model.Claim;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name="UserMicroservice", url="http://localhost:8100/user")
public interface UserFeign {
    @PostMapping("/saveClaim")
    public Claim submitClaim(@RequestBody Claim claimDetails);

    @GetMapping(value = "/getClaimsByEmailId/{email}")
    public List<Claim> getClaimsByEmail(@PathVariable String email);

    @GetMapping(value = "/getClaimById/{id}")
    public Claim getClaimById(@PathVariable long id);

    @PutMapping(value = "/updateClaim")
    public Claim updateClaim(@RequestBody Claim updatingDetails) ;

    @DeleteMapping(value="/deleteClaim/{claimId}")
    public String deleteClaim(@PathVariable long claimId);


    @GetMapping(value = "/downloadClaim/{claimId}")
    public String downloadClaim(@PathVariable long claimId);

    @GetMapping(value = "/getClaims")
    public List<Claim> getClaims() ;

    @GetMapping(value="/searchByEmail/{emailId}")
    public List<Claim> searchClaimByEmailId(@PathVariable String emailId);

}
