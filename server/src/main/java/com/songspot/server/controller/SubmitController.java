package com.songspot.server.controller;

import com.songspot.server.controller.model.CreateDemoTrack;
import com.songspot.server.controller.model.DemoTrack;
import com.songspot.server.repository.DemoTrackDaoJpa;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public class SubmitController {

    protected static final String CREATE_TRACK_ROUTE = "/submit/demotrack";
    protected static final String SUBMIT_TRACK_ROUTE = "/submit/demotrack/{trackId}/{curator}";

    @Autowired
    private DemoTrackDaoJpa trackDaoJpa;

    @PostMapping(CREATE_TRACK_ROUTE)
    @ResponseStatus(HttpStatus.CREATED)
    public DemoTrack createDemoTrack(@Valid @RequestBody CreateDemoTrack createDemoTrack) {
        return trackDaoJpa.createDemoTrack(null, createDemoTrack);
    }

    @PostMapping(SUBMIT_TRACK_ROUTE)
    @ResponseStatus(HttpStatus.OK)
    public DemoTrack submitTrackToCurator(@Valid @PathVariable Long trackId, @Valid @PathVariable String curator) {
        return trackDaoJpa.submit(null, trackId, curator);
    }
}