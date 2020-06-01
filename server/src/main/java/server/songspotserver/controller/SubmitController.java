package server.songspotserver.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import server.songspotserver.repository.TrackDemoDaoJpa;

@RestController
public class SubmitController {

    protected static final String SUBMIT_ROUTE = "/submit/{curatorId}/demotrack";

    @Autowired
    private TrackDemoDaoJpa trackDemoDaoJpa;
    
}
