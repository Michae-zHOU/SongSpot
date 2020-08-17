package com.songspot.server.repository;

import com.songspot.server.authentication.AuthenticationService;
import com.songspot.server.controller.model.User;
import com.songspot.server.controller.model.UserLoginParam;
import com.songspot.server.controller.model.UserRegisterParam;
import com.songspot.server.exception.ResourceDuplicatedException;
import com.songspot.server.exception.ResourceNotFoundException;
import com.songspot.server.exception.UserAuthenticationException;
import com.songspot.server.repository.model.Curator;
import com.songspot.server.repository.model.UserToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.Optional;

@Repository
public class CuratorDaoJpa {

    @Autowired
    private CuratorRepository curatorRepository;

    @Autowired
    private AuthenticationService authenticationService;

    @Autowired
    private UserTokenRepository userTokenRepository;

    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
    public com.songspot.server.controller.model.User createCurator(UserRegisterParam userRegisterParam) {
        String username = userRegisterParam.getUsername();
        Optional<Curator> existed = this.curatorRepository.findByName(username);
        if (existed.isPresent())
            throw new ResourceDuplicatedException("Username by " + username + " is duplicated");

        Curator curator = new Curator();
        curator.setName(username);
        curator.setPassword(this.authenticationService.encode(userRegisterParam.getPassword()));
        curator.setEmail(userRegisterParam.getEmail());
        curator.setAvatarNonPrimitive(userRegisterParam.getAvatar());
        curator.setUserType(userRegisterParam.getUserType().getUserTypeValue());
        curator.setBios(userRegisterParam.getDescription());
        curator.setWebsite(userRegisterParam.getWebsite());
        curator.setCreatedAt(Timestamp.from(Instant.now()));
        curator.setUpdatedAt(Timestamp.from(Instant.now()));

        this.curatorRepository.save(curator);

        return curator.toUserPresentational();
    }

    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
    public com.songspot.server.controller.model.User getCurator(UserLoginParam userLoginParam) {
        String username = userLoginParam.getUsername();
        Curator curator = this.curatorRepository.findByName(username)
                .orElseThrow(() -> new ResourceNotFoundException("Username by " + username + " is not found"));

        if (this.authenticationService.notMatches(userLoginParam.getPassword(), curator.getPassword()))
            throw new UserAuthenticationException("User " + username + " failed to log in");

        User user = curator.toUserPresentational();
        UserToken token = authenticationService.generateUserToken(user);
        this.userTokenRepository.save(token);

        return user;
    }
}
