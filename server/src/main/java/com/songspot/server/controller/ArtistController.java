package com.songspot.server.controller;

import com.songspot.server.controller.model.CreateDemoTrack;
import com.songspot.server.controller.model.DemoTrack;
import com.songspot.server.repository.DemoTrackDaoJpa;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class ArtistController {

    protected static final String ARTIST_CREATE_TRACK_ROUTE = "/artist/create";
    protected static final String ARTIST_VIEW_TRACKS_ROUTE = "/artist/view/tracks";

    @Autowired
    private DemoTrackDaoJpa trackDaoJpa;

    @PostMapping(ARTIST_CREATE_TRACK_ROUTE)
    @ResponseStatus(HttpStatus.CREATED)
    public DemoTrack createDemoTrack(@Valid @RequestBody CreateDemoTrack createDemoTrack) {
        return trackDaoJpa.createDemoTrack(getRequesterUsername(), createDemoTrack);
    }

    @GetMapping(ARTIST_VIEW_TRACKS_ROUTE)
    @ResponseStatus(HttpStatus.OK)
    public List<DemoTrack> getDemoTrack() {
        return trackDaoJpa.getDemoTrackOfArtist(getRequesterUsername());
    }

    private String getRequesterUsername() {
        return "default_user";
    }
}