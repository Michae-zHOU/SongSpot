package com.songspot.server.controller.model;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class UserLoginParam {

    @NotBlank
    @Size(min = 1, max = 100)
    private String username;

    @NotBlank
    @Size(min = 1, max = 100)
    private String password;

    public UserLoginParam(
            @NotBlank @Size(min = 1, max = 100) String username,
            @NotBlank @Size(min = 1, max = 100) String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
