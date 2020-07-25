package com.songspot.server.repository;

import com.songspot.server.repository.model.Curator;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CuratorRepository extends JpaRepository<Curator, Long> {
    Optional<Curator> findByName(String name);
}
