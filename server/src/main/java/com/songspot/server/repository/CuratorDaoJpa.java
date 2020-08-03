package com.songspot.server.repository;

import com.songspot.server.controller.model.UserRegisterParam;
import com.songspot.server.repository.model.Curator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.time.Instant;

@Repository
public class CuratorDaoJpa {

    @Autowired
    private CuratorRepository curatorRepository;

    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
    public com.songspot.server.controller.model.User createCurator(UserRegisterParam userRegisterParam) {
        Curator curator = new Curator();

        curator.setName(userRegisterParam.getUsername());
        curator.setPassword(userRegisterParam.getPassword());
        curator.setEmail(userRegisterParam.getEmail());
        curator.setAvatarNonPrimitive(userRegisterParam.getAvatar());
        curator.setUserType(userRegisterParam.getUserType().getUserTypeValue());
        curator.setBios(userRegisterParam.getDescription());
        curator.setWebsite(userRegisterParam.getWebsite());
        curator.setCreatedAt(Timestamp.from(Instant.now()));
        curator.setUpdatedAt(Timestamp.from(Instant.now()));

        curatorRepository.save(curator);
        return curator.toUserPresentational();
    }
}
