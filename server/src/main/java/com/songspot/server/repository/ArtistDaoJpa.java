package com.songspot.server.repository;

import com.songspot.server.authentication.AuthenticationService;
import com.songspot.server.controller.model.UserLoginParam;
import com.songspot.server.controller.model.UserRegisterParam;
import com.songspot.server.exception.ResourceDuplicatedException;
import com.songspot.server.exception.ResourceNotFoundException;
import com.songspot.server.exception.UserAuthenticationException;
import com.songspot.server.repository.model.Artist;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.Objects;

@Repository
public class ArtistDaoJpa {

    @Autowired
    private ArtistRepository artistRepository;

    @Autowired
    private AuthenticationService authenticationService;

    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
    public com.songspot.server.controller.model.User createArtist(UserRegisterParam userRegisterParam) {
        String username = userRegisterParam.getUsername();
        if (!Objects.isNull(this.artistRepository.findByName(username)))
            throw new ResourceDuplicatedException("Username by " + username + " is duplicated");

        Artist artist = new Artist();

        artist.setName(userRegisterParam.getUsername());
        artist.setPassword(this.authenticationService.encode(userRegisterParam.getPassword()));
        artist.setEmail(userRegisterParam.getEmail());
        artist.setAvatarNonPrimitive(userRegisterParam.getAvatar());
        artist.setUserType(userRegisterParam.getUserType().getUserTypeValue());
        artist.setBios(userRegisterParam.getDescription());
        artist.setWebsite(userRegisterParam.getWebsite());
        artist.setFollowsCount(0L);
        artist.setSongsCount(0L);
        artist.setCreatedAt(Timestamp.from(Instant.now()));
        artist.setUpdatedAt(Timestamp.from(Instant.now()));

        this.artistRepository.save(artist);
        return artist.toUserPresentational();
    }

    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
    public com.songspot.server.controller.model.User getArtist(UserLoginParam userLoginParam) {
        String username = userLoginParam.getUsername();
        Artist artist = this.artistRepository.findByName(username)
                .orElseThrow(() -> new ResourceNotFoundException("Username by " + username + " is not found"));

        if (!this.authenticationService.matches(userLoginParam.getPassword(), artist.getPassword()))
            throw new UserAuthenticationException("User " + username + " failed to log in");
        return artist.toUserPresentational();
    }
}
