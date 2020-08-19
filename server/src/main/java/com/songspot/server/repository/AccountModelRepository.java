package com.songspot.server.repository;

import com.songspot.server.repository.model.LinkedAccount;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountModelRepository extends JpaRepository<LinkedAccount, Long> {

}
