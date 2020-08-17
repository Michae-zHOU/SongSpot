package com.songspot.server.repository;

import com.songspot.server.repository.model.DemoTrack;
import com.songspot.server.repository.model.UserToken;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserTokenRepository extends JpaRepository<UserToken, Long> {
    Optional<UserToken> findById(Long id);

    List<UserToken> findAllByUsernameAndUserType(String username, Integer userType);

    Optional<UserToken> findByToken(String token);
}
