package com.songspot.server.repository;

import com.songspot.server.controller.model.UserRegisterParam;
import com.songspot.server.repository.model.Artist;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.time.Instant;

@Repository
public class ArtistDaoJpa {

    @Autowired
    private ArtistRepository artistRepository;

    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
    public com.songspot.server.controller.model.User createArtist(UserRegisterParam userRegisterParam) {
        Artist artist = new Artist();

        artist.setName(userRegisterParam.getUsername());
        artist.setPassword(userRegisterParam.getPassword());
        artist.setEmail(userRegisterParam.getEmail());
        artist.setAvatarNonPrimitive(userRegisterParam.getAvatar());
        artist.setUserType(userRegisterParam.getUserType().getUserTypeValue());
        artist.setBios(userRegisterParam.getDescription());
        artist.setWebsite(userRegisterParam.getWebsite());
        artist.setCreatedAt(Timestamp.from(Instant.now()));
        artist.setUpdatedAt(Timestamp.from(Instant.now()));

        artistRepository.save(artist);
        return artist.toUserPresentational();
    }
}
