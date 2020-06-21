package com.songspot.server.repository.model;

import javax.persistence.Column;
import javax.validation.constraints.NotBlank;

public abstract class UserModel extends NamedModel {

    @NotBlank
    @Column(name = "password", nullable = false)
    private String password;

    @NotBlank
    @Column(name = "user_type", nullable = false)
    private Long userType;

    @Column(name = "avatar")
    private Byte[] avatar;

    @Column(columnDefinition = "TEXT", name = "bio")
    private String bios;

    @Column(name = "website")
    private String website;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Long getUserType() {
        return userType;
    }

    public void setUserType(Long userType) {
        this.userType = userType;
    }

    public Byte[] getAvatar() {
        return avatar;
    }

    public void setAvatar(Byte[] avatar) {
        this.avatar = avatar;
    }

    public String getBios() {
        return bios;
    }

    public void setBios(String bios) {
        this.bios = bios;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }
}
