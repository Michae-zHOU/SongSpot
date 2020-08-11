package com.songspot.server.authentication;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public String encode(String password) {
        return bCryptPasswordEncoder.encode(password);
    }

    public boolean matches(String password, String expected) {
        return this.bCryptPasswordEncoder.matches(password, expected);
    }
}
