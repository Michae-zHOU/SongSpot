package com.songspot.server.repository;

import com.songspot.server.repository.model.CuratorAccount;
import com.songspot.server.repository.model.CuratorAccountKey;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CuratorAccountRepository extends JpaRepository<CuratorAccount, CuratorAccountKey> {
    List<CuratorAccount> findAllByCuratorId(Long curator);

    List<CuratorAccount> findAllByCuratorIdAndValidated(Long curator, Boolean validated);

    List<CuratorAccount> findAllByAccountId(Long accountId);
}
