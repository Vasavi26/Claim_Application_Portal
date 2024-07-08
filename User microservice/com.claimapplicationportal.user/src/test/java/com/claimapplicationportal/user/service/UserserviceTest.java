package com.claimapplicationportal.user.service;

import com.claimapplicationportal.user.exception.ClaimException;
import com.claimapplicationportal.user.model.Claim;
import com.claimapplicationportal.user.repository.ClaimRepo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;
import static org.springframework.test.util.AssertionErrors.assertEquals;


@ContextConfiguration(classes = {Userservice.class})
@ExtendWith(SpringExtension.class)
class UserserviceTest {

    @MockBean
    private ClaimRepo claimRepo;

    @Autowired
    private Userservice userservice;

    @Test
    void testSubmitClaim() throws ClaimException {
        Claim claimDetails=new Claim(101,"Munny",22,"Female",new ArrayList<>(),9876L,"v@gmail.com",1L,"H1","Hyderabad","Telangana","abc","a","a","a","a","a","a","a");
        Optional<Claim> ofResult = Optional.of(claimDetails);
        when(claimRepo.findById(claimDetails.getClaimId())).thenReturn(Optional.empty());
        when(claimRepo.save(claimDetails)).thenReturn(claimDetails);

        Claim oldClaim=userservice.submitClaim(claimDetails);
        Assertions.assertEquals(claimDetails,oldClaim);


        verify(claimRepo,times(1)).findById(claimDetails.getClaimId());
        verify(claimRepo,times(1)).save(claimDetails);


    }

//    test2
    @Test
    void testSubmitClaim1() throws ClaimException {
        Claim claimDetails = new Claim(101, "Munny", 22, "Female", new ArrayList<>(), 9876L, "v@gmail.com", 1L, "H1", "Hyderabad", "Telangana", "abc", "a", "a", "a", "a","a","a","a");
        Optional<Claim> ofResult = Optional.of(claimDetails);
        when(claimRepo.findById(claimDetails.getClaimId())).thenReturn(ofResult);
        when(claimRepo.save(claimDetails)).thenReturn(claimDetails);

        Exception exception=assertThrows(Exception.class,()->{
            userservice.submitClaim(claimDetails);
        });
        assertEquals("Primary key violation (Record is already present)",exception.getMessage());

        verify(claimRepo, times(1)).findById(claimDetails.getClaimId());
        verify(claimRepo, never()).save(claimDetails);
    }

    @Test
    void testGetClaimByEmailId() throws ClaimException {
        Claim claimDetails = new Claim(101, "Munny", 22, "Female", new ArrayList<>(), 9876L, "v@gmail.com", 1L, "H1", "Hyderabad", "Telangana", "abc", "a", "a", "a", "a","a","a","a");
        List<Claim> list=new ArrayList<>();
        list.add(claimDetails);
        when(claimRepo.findByEmail("v@gmail.com")).thenReturn(list);
        List<Claim> actualList=userservice.getClaimsByEmail("v@gmail.com");
        Assertions.assertEquals(list,actualList);

        verify(claimRepo,times(1)).findByEmail("v@gmail.com");

    }

    @Test
    void testGetClaimByEmailId1() throws ClaimException {
        List<Claim> list = new ArrayList<>();
        when(claimRepo.findByEmail("v@gmail.com")).thenReturn(list);

        Exception exception=assertThrows(Exception.class,()->{
            userservice.getClaimsByEmail("v@gmail.com");
        });

        assertEquals("No claims available with this emailId : v@gmail.com",exception.getMessage());
        verify(claimRepo, times(1)).findByEmail("v@gmail.com");

    }

    @Test
    void testGetClaimById() {
        Claim claimDetails = new Claim(101, "Munny", 22, "Female", new ArrayList<>(), 9876L, "v@gmail.com", 1L, "H1", "Hyderabad", "Telangana", "abc", "a", "a", "a", "a","a","a","a");
        when(claimRepo.getReferenceById(101L)).thenReturn(claimDetails);
        Claim actualData=userservice.getClaimById(101L);
        Assertions.assertEquals(actualData,claimDetails);
        verify(claimRepo,times(1)).getReferenceById(101L);

    }

    @Test
    void testUpdateClaim() throws ClaimException {
        Claim claimDetails = new Claim(101, "Munny", 22, "Female", new ArrayList<>(), 9876L, "v@gmail.com", 1L, "H1", "Hyderabad", "Telangana", "abc", "a", "a", "a", "a","a","a","a");
       Optional<Claim> optionalClaim=Optional.of(claimDetails);
        when(claimRepo.findById(101L)).thenReturn(optionalClaim);
        when(claimRepo.save(claimDetails)).thenReturn(claimDetails);
        Claim actualData=userservice.updateClaim(claimDetails);
        Assertions.assertEquals(actualData,claimDetails);
        verify(claimRepo,times(1)).findById(101L);
        verify(claimRepo,times(1)).save(claimDetails);


    }

    @Test
    void testUpdateClaim1() throws ClaimException {
        Claim claimDetails = new Claim(101, "Munny", 22, "Female", new ArrayList<>(), 9876L, "v@gmail.com", 1L, "H1", "Hyderabad", "Telangana", "abc", "a", "a", "a", "a","a","a","a");
        when(claimRepo.findById(101L)).thenReturn(Optional.empty());

        Exception exception=assertThrows(Exception.class,()->{
            userservice.updateClaim(claimDetails);
        });
        assertEquals("Failed to update claim with claimId : 101",exception.getMessage());
        verify(claimRepo,times(1)).findById(101L);
        verify(claimRepo,never()).save(claimDetails);

    }

    @Test
    void testDeleteClaim() throws ClaimException {
        doNothing().when(claimRepo).deleteById(101L);
        String Data=userservice.deleteClaim(101L);
        assertEquals("Successfully Claim Deleted",Data);
        verify(claimRepo,times(1)).deleteById(101L);

    }

//    @Test
//    void testDeleteClaim1() throws ClaimException {
//        Claim claimDetails = new Claim(101, "Munny", 22, "Female", "S1", 9876L, "v@gmail.com", 1L, "H1", "Hyderabad", "Telangana", "abc", "a", "a", "a", "a");
//        claimRepo.save(claimDetails);
//        doNothing().when(claimRepo).deleteById(1L);
//        Exception exception=assertThrows(Exception.class,()->{
//            userservice.deleteClaim(1L);
//        });
//        verify(claimRepo,times(1)).deleteById(1L);
//
//
//    }

    @Test
    void testGetClaims() throws ClaimException {
     Claim claimDetails = new Claim(101, "Munny", 22, "Female", new ArrayList<>(), 9876L, "v@gmail.com", 1L, "H1", "Hyderabad", "Telangana", "abc", "a", "a", "a", "a","a","a","a");
      List<Claim> list=new ArrayList<>();
      list.add(claimDetails);
      when(claimRepo.findAll()).thenReturn(list);
      List<Claim> actualData=userservice.getClaims();
      Assertions.assertEquals(actualData,list);
      verify(claimRepo,times(1)).findAll();

    }

    @Test
    void testGetClaims1() throws ClaimException {
        List<Claim> list=new ArrayList<>();
        when(claimRepo.findAll()).thenReturn(list);
        Exception exception=assertThrows(Exception.class,()->{
            userservice.getClaims();
        });
        assertEquals("No claims available: ",exception.getMessage());


        verify(claimRepo,times(1)).findAll();

    }

    @Test
    void testSearchClaimByEmailID(){
        Claim claimDetails = new Claim(101, "Munny", 22, "Female", new ArrayList<>(), 9876L, "v@gmail.com", 1L, "H1", "Hyderabad", "Telangana", "abc", "a", "a", "a", "a","a","a","a");
        List<Claim> list=new ArrayList<>();
        list.add(claimDetails);
        when(claimRepo.searchClaimByEmailId("v@gmail.com")).thenReturn(list);
        List<Claim> actualData=userservice.searchClaimByEmailId("v@gmail.com");
        Assertions.assertEquals(actualData,list);
        verify(claimRepo,times(1)).searchClaimByEmailId("v@gmail.com");

    }

    @Test
    void testDownloadClaim() throws FileNotFoundException {
        Claim claimDetails = new Claim(101, "Munny", 22, "Female", new ArrayList<>(), 9876L, "v@gmail.com", 1L, "H1", "Hyderabad", "Telangana", "abc", "a", "a", "a", "a","a","a","a");
        when(claimRepo.getReferenceById(101L)).thenReturn(claimDetails);
        String actualData=userservice.downloadClaim(101L);
      assertEquals("C:\\Users\\2135400\\OneDrive - Cognizant\\Documents\\Claim Portal Frontend\\ClaimPortalFrontend\\src\\assets\\ClaimDetails.pdf",actualData);
      verify(claimRepo,times(1)).getReferenceById(101L);

    }



}


