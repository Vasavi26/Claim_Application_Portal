package com.claimapplicationportal.user.service;

import com.claimapplicationportal.user.exception.ClaimException;
import com.claimapplicationportal.user.model.Claim;
import com.claimapplicationportal.user.pdfconfiguration.PdfGenerator;
import com.claimapplicationportal.user.repository.ClaimRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class Userservice {
    @Autowired
    private ClaimRepo claimRepo;


    public Claim submitClaim(Claim claimDetails) throws ClaimException {
        if(!claimRepo.findById(claimDetails.getClaimId()).isPresent()){
            claimDetails.setClaimRegisteredDate(String.valueOf(LocalDate.now()));
            return claimRepo.save(claimDetails);
        }else throw new ClaimException("Primary key violation (Record is already present)");
    }
    public List<Claim> getClaimsByEmail(String email) throws ClaimException {
        List<Claim> claims=claimRepo.findByEmail(email);
        if(!claims.isEmpty()){
            return claims;
        }else throw new ClaimException("No claims available with this emailId : "+email);
    }

    public Claim getClaimById(long id)
    {

        return claimRepo.getReferenceById(id);
    }

    public Claim updateClaim(Claim updatingDetails) throws ClaimException {
        Optional<Claim> claimDetails= claimRepo.findById(updatingDetails.getClaimId());
        if(claimDetails.isPresent()){
            claimDetails.get().setFullName(updatingDetails.getFullName());
            claimDetails.get().setAge(updatingDetails.getAge());
            claimDetails.get().setClaimAmount(updatingDetails.getClaimAmount());
            claimDetails.get().setStatus(updatingDetails.getStatus());
            claimDetails.get().setComments(updatingDetails.getComments());
            claimDetails.get().setUpdatedBy(claimDetails.get().getFullName());
            claimDetails.get().setUpdatedOn(String.valueOf(LocalDate.now()));
            return claimRepo.save(claimDetails.get());
        }else throw new ClaimException("Failed to update claim with claimId : "+updatingDetails.getClaimId());
    }

    public String deleteClaim(long claimId) throws ClaimException {
        try{
            claimRepo.deleteById(claimId);
            return "Successfully Claim Deleted";
        }
        catch(Exception e){
            throw new ClaimException(e.toString());
        }
    }
    public List<Claim> getClaims() throws ClaimException {
        List<Claim> claims=claimRepo.findAll();
        if(!claims.isEmpty()){
            return claims;
        }else throw new ClaimException("No claims available: ");

    }

    public List<Claim> searchClaimByEmailId(String email){
        return claimRepo.searchClaimByEmailId(email);
    }

    public String downloadClaim(long claimId){
        PdfGenerator pdfGenerator=new PdfGenerator();
        Claim claim=claimRepo.getReferenceById(claimId);
        return pdfGenerator.writeIntoFile(claim);


    }


}
