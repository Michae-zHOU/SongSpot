package com.songspot.server.controller.model;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class UserRegisterParam {

    @NotBlank
    @Size(min = 1, max = 100)
    private String username;

    @NotBlank
    @Size(min = 1, max = 100)
    private String password;

    @NotBlank
    @Size(min = 1, max = 100)
    private String email;

    private String description;

    @Size(min = 1, max = 100)
    private String website;

    private Byte[] avatar;

    public UserRegisterParam(@NotBlank @Size(min = 1, max = 100) String username,
                             @NotBlank @Size(min = 1, max = 100) String password,
                             @NotBlank @Size(min = 1, max = 100) String email,
                             String description,
                             String website,
                             Byte[] avatar) {
        this.username = username;
        this.password = password;
        this.description = description;
        this.email = email;
        this.website = website;
        this.avatar = avatar;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public Byte[] getAvatar() {
        return avatar;
    }

    public void setAvatar(Byte[] avatar) {
        this.avatar = avatar;
    }
}
