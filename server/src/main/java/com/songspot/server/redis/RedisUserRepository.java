package com.songspot.server.redis;

import com.songspot.server.controller.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RedisUserRepository extends CrudRepository<User, Long> {
}
