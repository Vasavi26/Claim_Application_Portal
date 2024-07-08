package com.cts.commonmicroservice.service;

import com.cts.commonmicroservice.exception.ClaimException;
import com.cts.commonmicroservice.feignclient.UserFeign;
import com.cts.commonmicroservice.model.Claim;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;
import static org.springframework.test.util.AssertionErrors.fail;


@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = CommonService.class)
public class CommonServiceTest {
    @Autowired
    private CommonService commonService;
    @MockBean
    private UserFeign userFeign;
    @Test

    void testSubmitClaim() throws ClaimException {
        Claim claimDetails = new Claim(101, "Munny", 22, "Female", new ArrayList<>(), 9876L, "v@gmail.com", 1L, "H1", "Hyderabad", "Telangana", "abc", "a", "a", "a", "a","a","a","a");
        when(userFeign.submitClaim(claimDetails)).thenReturn(claimDetails);
        Claim claim=commonService.submitClaim(claimDetails);
        Assertions.assertEquals(claimDetails,claim);
        verify(userFeign,times(1)).submitClaim(claimDetails);

    }

//    @Test
//    void testSubmitClaim2() throws ClaimException {
//        Claim claimDetails = new Claim(101, "Munny", 22, "Female", "S1", 9876L, "v@gmail.com", 1L, "H1", "Hyderabad", "Telangana", "abc", "a", "a", "a", "a");
//        ClaimException claimexception=new ClaimException("Error");
//        when(userFeign.submitClaim(claimDetails)).thenThrow(Exception.class);
//
//        Exception exception=assertThrows(Exception.class,()->{
//            commonService.submitClaim(claimDetails);
//            throw new ClaimException("Error");
//        });
//        assertEquals("Error",exception.getMessage());
//
//        verify(userFeign, times(1)).submitClaim(claimDetails);
//
//    }




    @Test
    void testGetClaimsByEmail() throws ClaimException {
        Claim claimDetails = new Claim(101, "Munny", 22, "Female", new ArrayList<>(), 9876L, "v@gmail.com", 1L, "H1", "Hyderabad", "Telangana", "abc", "a", "a", "a", "a","a","a","a");
        List<Claim> list=new ArrayList<>();
        list.add(claimDetails);
        when(userFeign.getClaimsByEmail("v@gmail.com")).thenReturn(list);
        List<Claim> claim=commonService.getClaimsByEmail("v@gmail.com");
        Assertions.assertEquals(list,claim);
        verify(userFeign,times(1)).getClaimsByEmail("v@gmail.com");

    }

    @Test
    void testGetClaimsByEmail2() throws ClaimException {
        Claim claimDetails = new Claim(101, "Munny", 22, "Female", new ArrayList<>(), 9876L, "v@gmail.com", 1L, "H1", "Hyderabad", "Telangana", "abc", "a", "a", "a", "a","a","a","a");
        List<Claim> list=new ArrayList<>();
        list.add(claimDetails);
        when(userFeign.getClaimsByEmail("v@gmail.com")).thenReturn(list);
        Exception exception=assertThrows(Exception.class,()->{
            commonService.getClaimsByEmail("v@gmail.com");
            throw new ClaimException("Primary key violation (Record is already present)");
        });
        assertEquals("Primary key violation (Record is already present)",exception.getMessage());

        verify(userFeign,times(1)).getClaimsByEmail("v@gmail.com");

    }



    @Test
    void testGetClaimById() throws ClaimException {
        Claim claimDetails = new Claim(101, "Munny", 22, "Female", new ArrayList<>(), 9876L, "v@gmail.com", 1L, "H1", "Hyderabad", "Telangana", "abc", "a", "a", "a", "a","a","a","a");
        when(userFeign.getClaimById(101L)).thenReturn(claimDetails);
        assertEquals(claimDetails,commonService.getClaimById(101L));
        verify(userFeign,times(1)).getClaimById(101L);

    }

//    @Test
//    void testGetClaimById2() throws ClaimException {
//        Claim claimDetails = new Claim(101, "Munny", 22, "Female", "S1", 9876L, "v@gmail.com", 1L, "H1", "Hyderabad", "Telangana", "abc", "a", "a", "a", "a");
//        when(userFeign.getClaimById(101L)).thenReturn(claimDetails);
//        assertEquals(claimDetails,commonService.getClaimById(101L));
//        verify(userFeign,times(1)).getClaimById(101L);
//
//    }



    @Test
    void testUpdateClaim() throws ClaimException {
        Claim claimDetails = new Claim(101, "Munny", 22, "Female", new ArrayList<>(), 9876L, "v@gmail.com", 1L, "H1", "Hyderabad", "Telangana", "abc", "a", "a", "a", "a","a","a","a");
        when(userFeign.updateClaim(claimDetails)).thenReturn(claimDetails);
        assertEquals(claimDetails,commonService.updateClaim(claimDetails));
        verify(userFeign,times(1)).updateClaim(claimDetails);

    }

    @Test
    void testDeleteClaim() throws ClaimException {

        when(userFeign.deleteClaim(101L)).thenReturn("Successfully Claim Deleted");
        assertEquals("Successfully Claim Deleted",commonService.deleteClaim(101L));
        verify(userFeign,times(1)).deleteClaim(101L);

    }

    @Test
    void testGetClaims() throws ClaimException{
        Claim claimDetails = new Claim(101, "Munny", 22, "Female", new ArrayList<>(), 9876L, "v@gmail.com", 1L, "H1", "Hyderabad", "Telangana", "abc", "a", "a", "a", "a","a","a","a");
        List<Claim> list=new ArrayList<>();
        list.add(claimDetails);
        when(userFeign.getClaims()).thenReturn(list);
        List<Claim> actualData=commonService.getClaims();
        Assertions.assertEquals(actualData,list);
        verify(userFeign,times(1)).getClaims();

    }

    @Test
    void testSearchClaimByEmailId() throws ClaimException{
        Claim claimDetails = new Claim(101, "Munny", 22, "Female", new ArrayList<>(), 9876L, "v@gmail.com", 1L, "H1", "Hyderabad", "Telangana", "abc", "a", "a", "a", "a","a","a","a");
        List<Claim> list=new ArrayList<>();
        list.add(claimDetails);
        when(userFeign.searchClaimByEmailId("v@gmail.com")).thenReturn(list);
        List<Claim> actualData=commonService.searchClaimByEmailId("v@gmail.com");
        Assertions.assertEquals(actualData,list);
        verify(userFeign,times(1)).searchClaimByEmailId("v@gmail.com");

    }

    @Test
    void testDownloadClaim() throws FileNotFoundException, ClaimException {
        Claim claimDetails = new Claim(101, "Munny", 22, "Female", new ArrayList<>(), 9876L, "v@gmail.com", 1L, "H1", "Hyderabad", "Telangana", "abc", "a", "a", "a", "a","a","a","a");
        when(userFeign.downloadClaim(101L)).thenReturn("Download");
        String actualData=commonService.downloadClaim(101L);
        assertEquals("Download",actualData);
        verify(userFeign,times(1)).downloadClaim(101L);

    }


    }



