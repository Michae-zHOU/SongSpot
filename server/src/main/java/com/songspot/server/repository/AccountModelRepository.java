package com.songspot.server.repository;

import com.songspot.server.repository.model.AccountModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AccountModelRepository extends JpaRepository<AccountModel, Long> {
    List<AccountModel> findAllBy

}
