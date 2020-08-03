package com.songspot.server.repository.model;

import com.songspot.server.controller.model.User;
import com.songspot.server.controller.model.UserType;

import javax.persistence.Column;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.ArrayList;

public abstract class UserModel extends NamedModel {

    @NotBlank
    @Size(min = 2, max = 100)
    @Column(name = "password", nullable = false)
    private String password;

    @NotBlank
    @Size(min = 2, max = 100)
    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "user_type", nullable = false)
    private Integer userType;

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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getUserType() {
        return userType;
    }

    public void setUserType(Integer userType) {
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

    private byte[] getAvatarNonPrimitive() {
        Byte[] avatar = this.getAvatar();
        byte[] byteObject = new byte[avatar.length];
        for (int idx = 0; idx < avatar.length; idx++) {
            byteObject[idx] = avatar[idx];
        }
        return byteObject;
    }

    public void setAvatarNonPrimitive(byte[] avatorNonPrimitive) {
        Byte[] avatar = new Byte[avatorNonPrimitive.length];
        for (int idx = 0; idx < avatorNonPrimitive.length; idx++) {
            avatar[idx] = avatorNonPrimitive[idx];
        }
        this.setAvatar(avatar);
    }

    public User toUserPresentational() {
        User user = new User();
        user.setId(this.getId());
        user.setUsername(this.getName());
        user.setUserType(UserType.getType(this.getUserType()));
        user.setGenres(new ArrayList<>()); // TODO
        user.setAvatar(this.getAvatarNonPrimitive());
        user.setToken(null); // TODO
        return user;
    }
}
