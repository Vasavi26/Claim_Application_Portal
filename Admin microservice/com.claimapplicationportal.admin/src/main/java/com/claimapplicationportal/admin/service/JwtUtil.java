package com.claimapplicationportal.admin.service;

import com.claimapplicationportal.admin.model.Login;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
public class JwtUtil {

    /**
     * creating a secret key for token, can be changed to anything
     */
    private static final String SECRETKEY = "${jwt.secret}";

    /**
     * This method is used to extract the username from the token
     *
     * @param token in the string format
     * @returnn
     */
    public String extractUsername(String token) {

        return extractClaim(token, Claims::getSubject);

    }

    /**
     * This method is used to extract a particular claim for the token
     *
     * @param <T>
     * @param token
     * @param claimsResolver
     * @return
     */
    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {


        final Claims claims = extractAllClaims(token);


        return claimsResolver.apply(claims);

    }

    /**
     * This method is used to extract claims for the token
     *
     * @param token
     * @return
     */
    private Claims extractAllClaims(String token) {


        return Jwts.parser().setSigningKey(SECRETKEY).parseClaimsJws(token).getBody();

    }


    public String generateToken(Login login) {


        Map<String, Object> claims = new HashMap<>();

        return createToken(claims, login.getPassword());
    }

    /**
     * This method is used to create token based on the claims and subject given as
     * parameter. It will add a signature to the jwt token based on the algorithm
     * HS256.
     *
     * @param claims
     * @param subject
     * @return
     */
    private String createToken(Map<String, Object> claims, String subject) {


        return Jwts.builder().setClaims(claims).setSubject(subject)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + (1000*60*15)))
                .signWith(SignatureAlgorithm.HS256, SECRETKEY).compact();

    }

    /**
     * This method is used to validate token based on the given token and
     * userDetails as parameter. First from the token we will extract the username
     * and then will check in the database whether the token extracted username and
     * the user residing in database is same or not and also will check whether the
     * token has been expired or not
     *
     * @param token
     * @return
     */
    public Boolean validateToken(String token) {


        try {
            Jwts.parser().setSigningKey(SECRETKEY).parseClaimsJws(token).getBody();


            return true;
        } catch (Exception e) {

            return false;
        }

    }
}
