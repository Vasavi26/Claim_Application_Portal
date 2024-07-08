package com.claimapplicationportal.admin.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class AdminTest {


    @Test
    void testConstructor() {
        Admin actualAdmin = new Admin();
        actualAdmin.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        actualAdmin.setCreatedOn("Jan 1, 2020 8:00am GMT+0100");
        actualAdmin.setEmailId("42");
        actualAdmin.setPassword("iloveyou");
        actualAdmin.setUpdatedBy("2020-03-01");
        actualAdmin.setUpdatedOn("2020-03-01");
        actualAdmin.setUserId(1);
//        String actualToStringResult = actualAdmin.toString();
        assertEquals("Jan 1, 2020 8:00am GMT+0100", actualAdmin.getCreatedBy());
        assertEquals("Jan 1, 2020 8:00am GMT+0100", actualAdmin.getCreatedOn());
        assertEquals("42", actualAdmin.getEmailId());
        assertEquals("iloveyou", actualAdmin.getPassword());
        assertEquals("2020-03-01", actualAdmin.getUpdatedBy());
        assertEquals("2020-03-01", actualAdmin.getUpdatedOn());
        assertEquals(1, actualAdmin.getUserId());

    }

    /**
     * Method under test: {@link Admin#equals(Object)}
     */

}

