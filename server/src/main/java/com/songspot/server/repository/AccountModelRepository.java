package com.songspot.server.repository;

import com.songspot.server.repository.model.AccountModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountModelRepository extends JpaRepository<AccountModel, Long> {

}
