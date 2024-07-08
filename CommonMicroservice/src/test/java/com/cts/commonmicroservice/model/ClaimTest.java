package com.cts.commonmicroservice.model;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ClaimTest {
    @Test
    void testConstructor(){
        Claim claim=new Claim();
        claim.setClaimId(101);
        claim.setFullName("Munny");
        claim.setClaimAmount(1000);
        claim.setAge(22);
        claim.setAddress("Hyderabad");
        claim.setCityName("Hyd");
        claim.setUpdatedOn("qwert");
        claim.setUpdatedBy("asd");
        claim.setCreatedBy("asd");
        claim.setCreatedOn("asdfg");
        claim.setGender("Female");
        claim.setStateName("Telangana");
        claim.setTemplateId(1);
        claim.setHospitalId("H12");
        claim.setEmail("v@gmail.com");
        claim.setServices (new ArrayList<>());
        assertEquals(101,claim.getClaimId());
        assertEquals("Munny",claim.getFullName());
        assertEquals(new ArrayList<>(),claim.getServices());
        assertEquals("v@gmail.com",claim.getEmail());
        assertEquals("H12",claim.getHospitalId());
        assertEquals(1,claim.getTemplateId());
        assertEquals("Telangana",claim.getStateName());
        assertEquals("Hyd",claim.getCityName());
        assertEquals(22,claim.getAge());
        assertEquals(1000,claim.getClaimAmount());




    }

    @Test
    void TestConstructor1(){
        Claim claim = new Claim(101, "Munny", 22, "Female", new ArrayList<>(), 9876L, "v@gmail.com", 1L, "H1", "Hyderabad", "Telangana", "abc", "a", "a", "a", "a","a","a","a");
        assertEquals(101,claim.getClaimId());
        assertEquals("Munny",claim.getFullName());
        assertEquals(new ArrayList<>(),claim.getServices());
        assertEquals("v@gmail.com",claim.getEmail());
        assertEquals("H1",claim.getHospitalId());
        assertEquals(1,claim.getTemplateId());
        assertEquals("Telangana",claim.getStateName());
        assertEquals("abc",claim.getAddress());
        assertEquals("a",claim.getCreatedBy());
        assertEquals("a",claim.getCreatedOn());
        assertEquals("a",claim.getUpdatedBy());
        assertEquals("a",claim.getUpdatedOn());
        assertEquals(22,claim.getAge());
        assertEquals(9876,claim.getClaimAmount());

    }

}
