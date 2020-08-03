package com.songspot.server.controller;

import com.songspot.server.controller.model.*;
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
import org.springframework.web.bind.annotation.ResponseStatus;

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
        createCurator();

        Long trackId = createTrack();

        submitTrackToCurator(trackId);
    }

    public void createCurator() {
        byte[] avatar = new byte[]{0, 0, 0, 0};
        UserRegisterParam params = new UserRegisterParam(CURATOR,
                "12345678",
                "sai@gmail.com",
                UserType.CURATOR,
                "TikToker",
                "www.saige.com",
                avatar);

        ResponseEntity<User> response = this.restTemplate.exchange(
                ROOT_URL + randomServerPort + UserController.REGISTER_ROUTE,
                HttpMethod.POST,
                new HttpEntity<>(params),
                User.class);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        User user = response.getBody();
        assertNotNull(user);
        assertEquals(params.getUsername(), user.getUsername());
        byte[] returnAvatar = user.getAvatar();
        for(int idx = 0; idx < returnAvatar.length; idx++) {
            assertEquals(avatar[idx], returnAvatar[idx]);
        }
        assertEquals(UserType.CURATOR, user.getUserType());
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

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
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
