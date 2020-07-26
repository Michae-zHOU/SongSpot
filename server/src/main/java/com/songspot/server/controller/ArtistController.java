package com.songspot.server.controller;

import com.songspot.server.controller.model.CreateDemoTrack;
import com.songspot.server.controller.model.DemoTrack;
import com.songspot.server.repository.DemoTrackDaoJpa;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class ArtistController {

    protected static final String ARTIST_VIEW_ROUTE = "/artist/{curator}";

    @Autowired
    private DemoTrackDaoJpa trackDaoJpa;

    @GetMapping(ARTIST_VIEW_ROUTE)
    @ResponseStatus(HttpStatus.CREATED)
    public DemoTrack createDemoTrack(@Valid @RequestBody CreateDemoTrack createDemoTrack) {
        return trackDaoJpa.createDemoTrack(null, createDemoTrack);
    }
}