package com.songspot.server.redis.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import org.springframework.data.redis.core.RedisHash;

import org.springframework.data.annotation.Id;
import java.io.Serializable;

@RedisHash(value = "UserToken", timeToLive = 36000000)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserToken implements Serializable {

    @Id
    private String index;

    private String token;

    public UserToken() {
    }

    public UserToken(String index, String token) {
        this.index = index;
        this.token = token;
    }

    public String getIndex() {
        return index;
    }

    public void setIndex(String index) {
        this.index = index;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
