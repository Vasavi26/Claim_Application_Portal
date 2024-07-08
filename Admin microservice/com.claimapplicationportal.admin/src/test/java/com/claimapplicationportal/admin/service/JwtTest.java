package com.claimapplicationportal.admin.service;

import com.claimapplicationportal.admin.model.Login;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = JwtUtil.class)

public class JwtTest {
    @Autowired
    private JwtUtil jwtUtil;
    @Test
    void testGenerateToken2() {
        Login login = mock(Login.class);
        when(login.getPassword()).thenReturn("iloveyou");
        doNothing().when(login).setPassword((String) any());
        doNothing().when(login).setEmailId((String) any());
        login.setPassword("iloveyou");
        login.setEmailId("janedoe");
        jwtUtil.generateToken(login);
        verify(login).getPassword();
        verify(login).setPassword((String) any());
        verify(login).setEmailId((String) any());
    }

    @Test
    void testValidateToken() {
        assertFalse(jwtUtil.validateToken("ABC123"));
        assertFalse(jwtUtil.validateToken("${jwt.secret}"));
        assertFalse(jwtUtil.validateToken("com.example.authorization.config.JwtUtil"));
        assertFalse(jwtUtil.validateToken(""));
        assertFalse(jwtUtil.validateToken("${jwt.secret}${jwt.secret}"));
    }
}
