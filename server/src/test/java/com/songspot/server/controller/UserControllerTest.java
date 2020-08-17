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
import org.springframework.web.bind.annotation.ResponseStatus;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;


@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
public class UserControllerTest {

    private static final String USER = "UserController_User";
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
        Long registerUserId = createCurator();

        Long loginUserId = LoginCurator();

        assertEquals(registerUserId, loginUserId);
    }

    public Long LoginCurator() {
        UserLoginParam params = new UserLoginParam(CURATOR, CURATOR_PASSWORD, UserType.CURATOR);
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
        assertEquals(UserType.CURATOR, user.getUserType());
        return user.getId();
    }

    public Long createCurator() {
        byte[] avatar = new byte[]{0, 0, 0, 0};
        UserRegisterParam params = new UserRegisterParam(CURATOR,
                CURATOR_PASSWORD,
                "usercontrollercurator@gmail.com",
                UserType.CURATOR,
                "UserController_Curator_Description",
                "www.usercontrollercurator.com",
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
        for (int idx = 0; idx < returnAvatar.length; idx++) {
            assertEquals(avatar[idx], returnAvatar[idx]);
        }
        assertEquals(UserType.CURATOR, user.getUserType());
        return user.getId();
    }
}
