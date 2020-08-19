package com.songspot.server.controller.util;

import com.songspot.server.client.UserClient;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class TestHelpers {

    public static <T> HttpEntity actAsOtherUser(T body, String username, String... eTags) {
        final HttpHeaders headers = new HttpHeaders();
        headers.set(UserClient.USER_NAME_KEY, username);

        if (eTags.length > 0)
            headers.setIfNoneMatch(eTags[0]);

        if (body == null) {
            return new HttpEntity<>(headers);
        }

        return new HttpEntity<>(body, headers);
    }


    public static <T> HttpEntity appendAuthToken(T body, String token, String... eTags) {
        final HttpHeaders headers = new HttpHeaders();
        headers.set(UserClient.USER_TOKEN_KEY, token);

        if (eTags.length > 0)
            headers.setIfNoneMatch(eTags[0]);

        if (body == null) {
            return new HttpEntity<>(headers);
        }

        return new HttpEntity<>(body, headers);
    }

    public static void assertByteArray(byte[] expected, byte[] actual) {
        assertEquals(expected.length, actual.length);
        for(int idx = 0; idx < expected.length; idx++) {
            assertEquals(expected[idx], actual[idx]);
        }
    }

    public static <T> void assertContains(List<T> container, T target) {
        boolean flag = false;
        for(T element : container) {
            if(element.equals(target)) {
                flag = true;
                break;
            }
        }
        assertTrue(flag);
    }
}