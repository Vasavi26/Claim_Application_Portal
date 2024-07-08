package com.claimapplicationportal.admin.exception;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;

class AdminExceptionTest {
    /**
     * Method under test: {@link AdminException#AdminException(String)}
     */
    @Test
    void testConstructor() {
        AdminException actualAdminException = new AdminException("An error occurred");
        assertNull(actualAdminException.getCause());
        assertEquals(0, actualAdminException.getSuppressed().length);
        assertEquals("An error occurred", actualAdminException.getMessage());
        assertEquals("An error occurred", actualAdminException.getLocalizedMessage());
    }
}

