package com.songspot.server.controller.model;

import javax.validation.constraints.NotBlank;

public class UserLogoutParam {

    @NotBlank
    private String username;

    @NotBlank
    private String token;

    public UserLogoutParam() {
    }

    public UserLogoutParam(@NotBlank String username, @NotBlank String token) {
        this.username = username;
        this.token = token;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
