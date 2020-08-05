package com.songspot.server.controller;

import com.songspot.server.controller.model.*;
import com.songspot.server.controller.util.TestHelpers;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
public class ArtistControllerTest {

    private static final String ARTIST = "NIU";
    private static final String CURATOR = "MZ";
    private static final String ROOT_URL = "http://localhost:";
    private final TestRestTemplate restTemplate = new TestRestTemplate();
    @LocalServerPort
    int randomServerPort;

    @Before
    public void setup() {
        restTemplate.getRestTemplate().setRequestFactory(new HttpComponentsClientHttpRequestFactory());
    }

    @Test
    public void testArtistController() {
        Long curatorId = createCurator();

        Long artistId = createArtist();

        DemoTrack track = createDemoTrack();

        validateGetDemoTrack(track);
    }

    public void validateGetDemoTrack(DemoTrack original) {
        ResponseEntity<DemoTrack[]> response = this.restTemplate.exchange(
                ROOT_URL + randomServerPort + ArtistController.ARTIST_VIEW_TRACKS_ROUTE,
                HttpMethod.GET,
                TestHelpers.actAsOtherUser(null, ARTIST),
                DemoTrack[].class);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        List<DemoTrack> tracks = Arrays.asList(response.getBody());
        TestHelpers.assertContains(tracks, original);
    }

    public DemoTrack createDemoTrack() {
        byte[] sampleSong = {0, 0, 0, 0};
        CreateDemoTrack params = new CreateDemoTrack("Jazz.mp3", "mp3", null, sampleSong, ARTIST);

        ResponseEntity<DemoTrack> response = this.restTemplate.exchange(
                ROOT_URL + randomServerPort + ArtistController.ARTIST_CREATE_TRACK_ROUTE,
                HttpMethod.POST,
                TestHelpers.actAsOtherUser(params, ARTIST),
                DemoTrack.class);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());

        DemoTrack track = response.getBody();

        assertNotNull(track);
        assertNotNull(track.getFilename());
        assertEquals("Jazz.mp3", track.getFilename());

        assertNotNull(track.getArtist());
        assertEquals(ARTIST, track.getArtist());
        assertNotNull(track.getFileType());
        assertEquals("mp3", track.getFileType());

        return track;
    }

    public Long createArtist() {
        byte[] avatar = new byte[]{1, 1, 1, 1};
        UserRegisterParam params = new UserRegisterParam(ARTIST,
                "87654321",
                "niulaoban@gmail.com",
                UserType.ARTIST,
                "Soundclouder",
                "www.niulaoban.com",
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
        TestHelpers.assertByteArray(avatar, returnAvatar);
        assertEquals(UserType.ARTIST, user.getUserType());

        return user.getId();
    }

    public Long createCurator() {
        byte[] avatar = new byte[]{0, 0, 0, 0};
        UserRegisterParam params = new UserRegisterParam(CURATOR,
                "12345678",
                "mz@gmail.com",
                UserType.CURATOR,
                "WeChater",
                "www.mz.com",
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
        TestHelpers.assertByteArray(avatar, returnAvatar);
        assertEquals(UserType.CURATOR, user.getUserType());

        return user.getId();
    }
}