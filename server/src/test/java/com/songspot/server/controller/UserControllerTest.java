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
public class UserControllerTest {

    private static final String USER = "UserController_User";
    private static final String ARTIST = "UserController_ARTIST";
    private static final String ARTIST_PASSWORD = "UserController_Artist_Password";
    private static final String CURATOR = "UserController_Curator";
    private static final String CURATOR_PASSWORD = "UserController_Curator_Password";
    private static final String ROOT_URL = "http://localhost:";
    private final TestRestTemplate restTemplate = new TestRestTemplate();
    @LocalServerPort
    int randomServerPort;

    @Before
    public void setup() {
        restTemplate.getRestTemplate().setRequestFactory(new HttpComponentsClientHttpRequestFactory());
    }

    @Test
    public void testUserController() {
        User registerCurator = createCurator();
        User loginCurator = LoginCurator();

        assertEquals(registerCurator.getId(), loginCurator.getId());

        User registerArtist = createArtist();
        User loginArtist = LoginArtist();

        assertEquals(registerArtist.getId(), loginArtist.getId());

        validateCuratorTokenAuth(loginCurator);
    }

    public User createCurator() {
        byte[] avatar = new byte[]{0, 0, 0, 0};
        UserRegisterParam params = new UserRegisterParam(CURATOR,
                CURATOR_PASSWORD,
                "usercontrollercurator@gmail.com",
                UserType.CURATOR,
                "UserController_Curator_Description",
                "www.usercontrollercurator.com",
                avatar);

        return getUser(avatar, params, UserType.CURATOR);
    }

    public User createArtist() {
        byte[] avatar = new byte[]{0, 0, 0, 0};
        UserRegisterParam params = new UserRegisterParam(ARTIST,
                ARTIST_PASSWORD,
                "usercontrollerartist@gmail.com",
                UserType.ARTIST,
                "UserController_Artist_Description",
                "www.usercontrollerartist.com",
                avatar);

        return getUser(avatar, params, UserType.ARTIST);
    }

    public User LoginCurator() {
        UserLoginParam params = new UserLoginParam(CURATOR, CURATOR_PASSWORD, UserType.CURATOR);
        return getUser(params, UserType.CURATOR);
    }

    public User LoginArtist() {
        UserLoginParam params = new UserLoginParam(ARTIST, ARTIST_PASSWORD, UserType.ARTIST);
        return getUser(params, UserType.ARTIST);
    }

    private User getUser(byte[] avatar, UserRegisterParam params, UserType userType) {
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
        for (int idx = 0; idx < returnAvatar.length; idx++) {
            assertEquals(avatar[idx], returnAvatar[idx]);
        }
        assertEquals(userType, user.getUserType());
        return user;
    }

    private User getUser(UserLoginParam params, UserType userType) {
        ResponseEntity<User> response = this.restTemplate.exchange(
                ROOT_URL + randomServerPort + UserController.LOGIN_ROUTE,
                HttpMethod.POST,
                new HttpEntity<>(params),
                User.class);

        assertEquals(HttpStatus.FOUND, response.getStatusCode());
        User user = response.getBody();
        assertNotNull(user);
        assertEquals(params.getUsername(), user.getUsername());
        assertNotNull(user.getToken());
        assertEquals(userType, user.getUserType());
        return user;
    }


    public void validateCuratorTokenAuth(User user) {
        ResponseEntity<DemoTrack[]> response = this.restTemplate.exchange(
                ROOT_URL + randomServerPort + CuratorController.CURATOR_VIEW_ROUTE,
                HttpMethod.GET,
                TestHelpers.appendAuthToken(null, user.getToken()),
                DemoTrack[].class);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        List<DemoTrack> tracks = Arrays.asList(response.getBody());
        assertEquals(0, tracks.size());
    }
}
