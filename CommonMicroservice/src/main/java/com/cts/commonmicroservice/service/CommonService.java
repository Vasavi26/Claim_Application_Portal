package com.cts.commonmicroservice.service;

import com.cts.commonmicroservice.feignclient.UserFeign;
import com.cts.commonmicroservice.model.Claim;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommonService {

    @Autowired
    private UserFeign userClient;
    public Claim submitClaim(Claim claimDetails) {

            return userClient.submitClaim(claimDetails);



    }
    public List<Claim> getClaimsByEmail(String email)  {

            return userClient.getClaimsByEmail(email);


    }

    public Claim getClaimById(long id) {
            return userClient.getClaimById(id);


    }

    public Claim updateClaim(Claim updatingDetails) {

            return userClient.updateClaim(updatingDetails);


    }

    public String deleteClaim(long claimId)  {

            return userClient.deleteClaim(claimId);


    }
    public List<Claim> getClaims() {

            return userClient.getClaims();



    }

    public List<Claim> searchClaimByEmailId(String email)  {

            return userClient.searchClaimByEmailId(email);


    }

    public String downloadClaim(long claimId)  {

            return userClient.downloadClaim(claimId);


    }
}
