package com.songspot.server.controller;

import com.songspot.server.controller.model.CreateDemoTrack;
import com.songspot.server.controller.model.DemoTrack;
import com.songspot.server.repository.DemoTrackDaoJpa;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public class CuratorController {

    protected static final String CURATOR_VIEW_ROUTE = "/curator/view/{curator}";

    @Autowired
    private DemoTrackDaoJpa trackDaoJpa;

    @GetMapping(CURATOR_VIEW_ROUTE)
    @ResponseStatus(HttpStatus.CREATED)
    public DemoTrack createDemoTrack(@Valid @RequestBody CreateDemoTrack createDemoTrack) {
        return trackDaoJpa.createDemoTrack(null, createDemoTrack);
    }
}