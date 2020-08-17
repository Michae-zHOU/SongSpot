package com.songspot.server.repository.model;

import javax.persistence.*;

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

    @Column(name = "username", nullable = false, updatable = true)
    private String username;

    @Column(name = "token", nullable = false, updatable = true)
    private String token;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
