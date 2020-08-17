package com.songspot.server.authentication;

import com.songspot.server.configuration.JwtConfig;
import com.songspot.server.controller.model.User;
import com.songspot.server.controller.model.UserType;
import com.songspot.server.repository.model.UserToken;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.sql.Timestamp;
import java.time.Instant;

@Service
public class AuthenticationService {

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private SecretService secretService;

    @Autowired
    private JwtConfig jwtConfig;

    public String encode(String password) {
        return bCryptPasswordEncoder.encode(password);
    }

    public boolean notMatches(String password, String expected) {
        return !this.bCryptPasswordEncoder.matches(password, expected);
    }

    public UserToken generateUserToken(User user) {
        String token = generateToken(user);
        user.setToken(token);
        UserToken userToken = new UserToken();
        userToken.setUserId(user.getId());
        userToken.setUsername(user.getUsername());
        userToken.setUserType(user.getUserType().getUserTypeValue());
        userToken.setCreatedAt(Timestamp.from(Instant.now()));
        userToken.setUpdatedAt(Timestamp.from(Instant.now()));
        return userToken;
    }

    /**
     * Tries to parse specified String as a JWT token. If successful, returns User object with username, id and role prefilled (extracted from token).
     * If unsuccessful (token is invalid or not containing all required user properties), simply returns null.
     *
     * @param token the JWT token to parse
     * @return the User object extracted from specified token or null if a token is invalid.
     */
    public User parseToken(String token) {
        try {
            String secret = secretService.getSecretOf(SignatureAlgorithm.HS256);
            Claims body = Jwts.parserBuilder()
                    .setSigningKey(secret)
                    .build()
                    .parseClaimsJws(token)
                    .getBody();
            User user = new User();
            user.setUsername(body.getSubject());
            user.setId(Long.parseLong((String) body.get("userId")));
            user.setUserType(UserType.getType((String) body.get("userType")));

            return user;

        } catch (JwtException | ClassCastException e) {
            return null;
        }
    }

    /**
     * Generates a JWT token containing username as subject, and userId and role as additional claims. These properties are taken from the specified
     * User object. Tokens validity is infinite.
     *
     * @param user the user for which the token will be generated
     * @return the JWT token
     */
    private String generateToken(User user) {
        Claims claims = Jwts.claims().setSubject(user.getUsername());
        claims.put("userId", user.getId().toString());
        claims.put("userType", user.getUserType().toString());
        Key key = Keys.secretKeyFor(SignatureAlgorithm.HS256);
        String secret = secretService.getSecretOf(SignatureAlgorithm.HS256);

        return Jwts.builder()
                .setClaims(claims)
                .setSubject(secret)
                .signWith(key)
                .compact();
    }
}
