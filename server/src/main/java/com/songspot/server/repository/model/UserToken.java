package com.songspot.server.repository.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "user_tokens")
public class UserToken extends AuditModel {

    @Id
    @GeneratedValue(generator = "user_token_generator")
    @SequenceGenerator(
            name = "user_token_generator",
            sequenceName = "user_token_sequence",
            initialValue = 100
    )
    private Long id;

    @Column(name = "user_id", nullable = false, updatable = true)
    private Long userId;

    @Column(name = "username", nullable = false, updatable = true)
    private String username;

    @Column(name = "user_type", nullable = false, updatable = true)
    private Integer userType;

    @Column(name = "token", nullable = false, updatable = true)
    private String token;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Integer getUserType() {
        return userType;
    }

    public void setUserType(Integer userType) {
        this.userType = userType;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public com.songspot.server.redis.model.UserToken toCacheModel() {
        if (Objects.isNull(this.getUserId()) || Objects.isNull(this.getUserType()))
            throw new IllegalArgumentException("Token index is invalid");

        String index = this.getUserId() + "@" + this.getUserType();
        String token = this.getToken();

        if (Objects.isNull(token)) {
            throw new IllegalStateException("Token is null in UserToken");
        }

        return new com.songspot.server.redis.model.UserToken(index, token);
    }
}
