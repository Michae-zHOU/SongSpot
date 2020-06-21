package com.songspot.server.controller;

import com.songspot.server.controller.model.User;
import com.songspot.server.controller.model.UserLoginParam;
import com.songspot.server.controller.model.UserLogoutParam;
import com.songspot.server.controller.model.UserRegisterParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public class UserController {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);
    protected static final String LOGIN_ROUTE = "/login";
    protected static final String LOGOUT_ROUTE = "/logout";
    protected static final String REGISTER_ROUTE = "/register";

    @PostMapping(LOGIN_ROUTE)
    @ResponseStatus(HttpStatus.OK)
    public User userLogin(@Valid @RequestBody UserLoginParam userLoginParam) {
        return null;
    }

    @PostMapping(REGISTER_ROUTE)
    @ResponseStatus(HttpStatus.CREATED)
    public User userRegister(@Valid @RequestBody UserRegisterParam userRegisterParam) {
        return null;
    }

    @PostMapping(LOGOUT_ROUTE)
    @ResponseStatus(HttpStatus.OK)
    public User userLogout(@Valid @RequestBody UserLogoutParam userLogoutParam) {
        return null;
    }
}
