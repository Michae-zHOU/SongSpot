package com.songspot.server.controller;

import com.songspot.server.controller.model.*;
import com.songspot.server.repository.ArtistDaoJpa;
import com.songspot.server.repository.CuratorDaoJpa;
import com.songspot.server.repository.CuratorRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public class UserController {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);
    public static final String LOGIN_ROUTE = "/login";
    public static final String LOGOUT_ROUTE = "/logout";
    public static final String REGISTER_ROUTE = "/register";

    @Autowired
    private CuratorDaoJpa curatorDaoJpa;

    @Autowired
    private ArtistDaoJpa artistDaoJpa;

    @PostMapping(LOGIN_ROUTE)
    @ResponseStatus(HttpStatus.OK)
    public User userLogin(@Valid @RequestBody UserLoginParam userLoginParam) {
        return null;
    }

    @PostMapping(REGISTER_ROUTE)
    @ResponseStatus(HttpStatus.CREATED)
    public User userRegister(@Valid @RequestBody UserRegisterParam userRegisterParam) {
        UserType newUserType = userRegisterParam.getUserType();
        return switch (newUserType) {
            case CURATOR -> this.curatorDaoJpa.createCurator(userRegisterParam);
            case ARTIST -> this.artistDaoJpa.createArtist(userRegisterParam);
            default -> throw new IllegalArgumentException("New user registeration failed. Unknown user type.");
        };
    }

    @PostMapping(LOGOUT_ROUTE)
    @ResponseStatus(HttpStatus.OK)
    public User userLogout(@Valid @RequestBody UserLogoutParam userLogoutParam) {
        return null;
    }
}
