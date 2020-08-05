package com.songspot.server.repository.model;

import javax.persistence.*;

@Entity
@Table(name = "account")
public class AccountModel {
    @Id
    @GeneratedValue(generator = "account_generator")
    @SequenceGenerator(
            name = "account_generator",
            sequenceName = "account_sequence",
            initialValue = 100
    )
    private Long id;

    @Column(name = "platform", nullable = false, updatable = true)
    private String platform;

    @Column(name = "username", nullable = false, updatable = true)
    private String username;

    @Column(name = "followers", nullable = false, updatable = true)
    private Long followers;

    @Column(name = "link", nullable = false, updatable = true)
    private String link;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPlatform() {
        return platform;
    }

    public void setPlatform(String platform) {
        this.platform = platform;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Long getFollowers() {
        return followers;
    }

    public void setFollowers(Long followers) {
        this.followers = followers;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }
}
