package com.songspot.server.repository;

import com.songspot.server.repository.model.DemoTrackCurator;
import com.songspot.server.repository.model.DemoTrackCuratorKey;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DemoTrackCuratorRepository extends JpaRepository<DemoTrackCurator, DemoTrackCuratorKey> {

    List<DemoTrackCurator> findAllByCuratorId(Long curator);

    List<DemoTrackCurator> findAllByCuratorIdAndViewed(Long curator, Boolean viewed);

    List<DemoTrackCurator> findAllByDemoTrackId(Long demoTrackId);
}
