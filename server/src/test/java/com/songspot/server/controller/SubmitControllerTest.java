package com.songspot.server.controller;

import com.songspot.server.controller.model.CreateDemoTrack;
import com.songspot.server.controller.model.DemoTrack;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.*;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;


@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
public class SubmitControllerTest {

    private static final String USER = "AZ";
    private static final String CURATOR = "Sai";
    private static final String ROOT_URL = "http://localhost:";
    private final TestRestTemplate restTemplate = new TestRestTemplate();
    @LocalServerPort
    int randomServerPort;

    public static <T> HttpEntity actAsOtherUser(T body, String username, String... eTags) {
        final HttpHeaders headers = new HttpHeaders();
        headers.set(username, username);

        if (eTags.length > 0)
            headers.setIfNoneMatch(eTags[0]);

        if (body == null) {
            return new HttpEntity<>(headers);
        }

        return new HttpEntity<>(body, headers);
    }

    @Before
    public void setup() {
        restTemplate.getRestTemplate().setRequestFactory(new HttpComponentsClientHttpRequestFactory());
    }

    @Test
    public void testSubmitController() {
        Long trackId = createTrack();

        submitTrackToCurator(trackId);
    }

    public void submitTrackToCurator(Long trackId) {
        ResponseEntity<DemoTrack> response = this.restTemplate.exchange(
                ROOT_URL + randomServerPort + SubmitController.SUBMIT_TRACK_ROUTE,
                HttpMethod.POST,
                null,
                DemoTrack.class,
                trackId,
                CURATOR);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        DemoTrack track = response.getBody();

        assertNotNull(track);
        assertEquals(trackId, track.getId());
    }

    public Long createTrack() {
        byte[] testData = new byte[]{0, 0, 0, 0};
        CreateDemoTrack params = new CreateDemoTrack("Trance.mp3", "mp3", null, testData, USER);

        ResponseEntity<DemoTrack> response = this.restTemplate.exchange(
                ROOT_URL + randomServerPort + SubmitController.CREATE_TRACK_ROUTE,
                HttpMethod.POST,
                actAsOtherUser(params, USER),
                DemoTrack.class);

        DemoTrack track = response.getBody();

        assertNotNull(track);

        assertNotNull(track.getFilename());
        assertEquals("Trance.mp3", track.getFilename());

        assertNotNull(track.getArtist());
        assertEquals(USER, track.getArtist());

        assertNotNull(track.getFileType());
        assertEquals("mp3", track.getFileType());

        return track.getId();
    }
}
