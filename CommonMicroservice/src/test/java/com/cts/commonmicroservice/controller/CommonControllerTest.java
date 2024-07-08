package com.cts.commonmicroservice.controller;


import com.cts.commonmicroservice.model.Claim;
import com.cts.commonmicroservice.service.CommonService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CommonController.class)
public class CommonControllerTest {
    @Autowired
    private CommonController commonController;
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private CommonService commonService;
    @Test
    void testSubmitClaim() throws Exception {
        Claim claimDetails = new Claim(101, "Munny", 22, "Female", new ArrayList<>(), 9876L, "v@gmail.com", 1L, "H1", "Hyderabad", "Telangana", "abc", "a", "a", "a", "a","a","a","a");
        when(commonService.submitClaim(claimDetails)).thenReturn(claimDetails);

        mockMvc.perform(post("/saveClaim")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(claimDetails)))
                .andExpect(status().isOk());
//        verify(commonService,times(1)).submitClaim(claimDetails);
    }

    @Test
    void testGetClaimsByEmailId() throws Exception {
        Claim claimDetails = new Claim(101, "Munny", 22, "Female", new ArrayList<>(), 9876L, "v@gmail.com", 1L, "H1", "Hyderabad", "Telangana", "abc", "a", "a", "a", "a","a","a","a");
        List<Claim> list=new ArrayList<>();
        list.add(claimDetails);
        when(commonService.getClaimsByEmail("v@gmail.com")).thenReturn(list);

        mockMvc.perform(get("/getClaimsByEmailId/v@gmail.com"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].claimId").value(101))
                .andExpect(jsonPath("$[0].fullName").value("Munny"));
        verify(commonService,times(1)).getClaimsByEmail("v@gmail.com");
    }

    @Test
    void testGetClaimById() throws Exception {
        Claim claimDetails = new Claim(101, "Munny", 22, "Female", new ArrayList<>(), 9876L, "v@gmail.com", 1L, "H1", "Hyderabad", "Telangana", "abc", "a", "a", "a", "a","a","a","a");
        when(commonService.getClaimById(101L)).thenReturn(claimDetails);

        mockMvc.perform(get("/getClaimById/101"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("claimId").value(101))
                .andExpect(jsonPath("fullName").value("Munny"));
        verify(commonService,times(1)).getClaimById(101);
    }

    @Test
    void testUpdateClaim() throws Exception {
        Claim claimDetails = new Claim(101, "Munny", 22, "Female", new ArrayList<>(), 9876L, "v@gmail.com", 1L, "H1", "Hyderabad", "Telangana", "abc", "a", "a", "a", "a","a","a","a");
        when(commonService.updateClaim(claimDetails)).thenReturn(claimDetails);

        mockMvc.perform(put("/updateClaim")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(claimDetails)))
                .andExpect(status().isOk());
//        verify(commonService,times(1)).updateClaim(claimDetails);
    }

    @Test
    void testDeleteClaim() throws Exception {
        Claim claimDetails = new Claim(101, "Munny", 22, "Female", new ArrayList<>(), 9876L, "v@gmail.com", 1L, "H1", "Hyderabad", "Telangana", "abc", "a", "a", "a", "a","a","a","a");
        when(commonService.deleteClaim(101L)).thenReturn("Successfully Claim Deleted");

        mockMvc.perform(delete("/deleteClaim/101"))
                .andExpect(status().isOk());
        verify(commonService,times(1)).deleteClaim(101);
    }
    @Test
    void testDownloadClaim() throws Exception {
        Claim claimDetails = new Claim(101, "Munny", 22, "Female", new ArrayList<>(), 9876L, "v@gmail.com", 1L, "H1", "Hyderabad", "Telangana", "abc", "a", "a", "a", "a","a","a","a");
        when(commonService.downloadClaim(101L)).thenReturn("C:\\Users\\2135400\\OneDrive - Cognizant\\Documents\\Claim Portal Frontend\\ClaimPortalFrontend\\src\\assets\\ClaimDetails.pdf");

        mockMvc.perform(get("/downloadClaim/101"))
                .andExpect(status().isOk());
        verify(commonService,times(1)).downloadClaim(101);
    }
    @Test
    void testGetClaims() throws Exception {
        Claim claimDetails = new Claim(101, "Munny", 22, "Female", new ArrayList<>(), 9876L, "v@gmail.com", 1L, "H1", "Hyderabad", "Telangana", "abc", "a", "a", "a", "a","a","a","a");
        List<Claim> list=new ArrayList<>();
        list.add(claimDetails);
        when(commonService.getClaims()).thenReturn(list);

        mockMvc.perform(get("/getClaims"))
                .andExpect(status().isOk());
        verify(commonService,times(1)).getClaims();
    }
    @Test
    void testSearchByEmail() throws Exception {
        Claim claimDetails = new Claim(101, "Munny", 22, "Female", new ArrayList<>(), 9876L, "v@gmail.com", 1L, "H1", "Hyderabad", "Telangana", "abc", "a", "a", "a", "a","a","a","a");
        List<Claim> list=new ArrayList<>();
        list.add(claimDetails);
        when(commonService.searchClaimByEmailId("v@gmail.com")).thenReturn(list);

        mockMvc.perform(get("/searchByEmail/v@gmail.com"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].claimId").value(101))
                .andExpect(jsonPath("$[0].fullName").value("Munny"));


        verify(commonService,times(1)).searchClaimByEmailId("v@gmail.com");
    }



}
