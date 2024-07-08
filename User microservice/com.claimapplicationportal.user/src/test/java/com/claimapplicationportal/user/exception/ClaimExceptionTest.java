package com.claimapplicationportal.user.exception;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class ClaimExceptionTest {
    @Test
    void testConstructor(){
        ClaimException claimException=new ClaimException("Error");
    assertNull(claimException.getCause());
    assertEquals(0,claimException.getSuppressed().length);
    assertEquals("Error",claimException.getMessage());
    assertEquals("Error",claimException.getLocalizedMessage());
    }
}
