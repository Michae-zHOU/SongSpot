package com.songspot.server.repository;

import com.songspot.server.repository.model.DemoTrack;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface DemoTrackRepository extends JpaRepository<DemoTrack, Long> {
    Optional<DemoTrack> findById(Long id);

    List<DemoTrack> findAllByArtist(String artist);
}
