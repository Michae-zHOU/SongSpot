package com.songspot.server.redis;

import com.songspot.server.redis.model.UserToken;
import org.springframework.data.repository.CrudRepository;

public interface RedisUserTokenRepository extends CrudRepository<UserToken, String> {
    UserToken findByIndex(String index);
}
