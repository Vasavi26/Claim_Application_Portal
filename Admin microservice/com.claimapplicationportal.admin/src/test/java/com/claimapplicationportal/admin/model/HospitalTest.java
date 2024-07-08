package com.claimapplicationportal.admin.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.Test;

class HospitalTest {

    @Test
    void testConstructor() {
        Hospital actualHospital = new Hospital();
        actualHospital.setHospitalId("42");
        actualHospital.setHospitalName("Hospital Name");
//        String actualToStringResult = actualHospital.toString();
        assertEquals("42", actualHospital.getHospitalId());
        assertEquals("Hospital Name", actualHospital.getHospitalName());
//        assertEquals("Hospital(hospitalId=42, hospitalName=Hospital Name)", actualToStringResult);
    }
}

