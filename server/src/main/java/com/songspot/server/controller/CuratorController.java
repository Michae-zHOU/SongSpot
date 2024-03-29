package com.songspot.server.controller;

import com.songspot.server.client.UserClient;
import com.songspot.server.controller.model.DemoTrack;
import com.songspot.server.repository.DemoTrackDaoJpa;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class CuratorController {

    protected static final String CURATOR_VIEW_ROUTE = "/curator/view/";
    private static final Logger LOGGER = LoggerFactory.getLogger(CuratorController.class);
    @Autowired
    private DemoTrackDaoJpa trackDaoJpa;

    @GetMapping(CURATOR_VIEW_ROUTE)
    @ResponseStatus(HttpStatus.OK)
    public List<DemoTrack> getTracks() {
        try {
            String username = MDC.get(UserClient.USER_NAME_KEY);
            return trackDaoJpa.getDemoTrackOfCurator(username);
        } catch (Exception ex) {
            return new ArrayList<>();
        }
    }
}