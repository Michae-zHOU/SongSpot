package com.songspot.server.repository.model;

import javax.persistence.*;

@Entity
@Table(name = "artists")
public class Artist extends UserModel {
    @Id
    @GeneratedValue(generator = "artist_generator")
    @SequenceGenerator(
            name = "artist_generator",
            sequenceName = "artist_sequence",
            initialValue = 100
    )
    private Long id;

    @Column(name = "songs_count", nullable = false, updatable = true)
    private Long songsCount;

    @Column(name = "followers_count", nullable = false, updatable = true)
    private Long followsCount;

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public Long getId() {
        return id;
    }

    public Long getSongsCount() {
        return songsCount;
    }

    public void setSongsCount(Long songsCount) {
        this.songsCount = songsCount;
    }

    public Long getFollowsCount() {
        return followsCount;
    }

    public void setFollowsCount(Long followsCount) {
        this.followsCount = followsCount;
    }
}