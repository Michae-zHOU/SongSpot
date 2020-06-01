package server.songspotserver.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import server.songspotserver.repository.model.DemoTrack;

import java.util.List;
import java.util.Optional;

@Repository
public interface TrackDemoRepository extends JpaRepository<DemoTrack, Long> {
    Optional<DemoTrack> findById(Long id);

    List<DemoTrack> findAllByArtist(String artist);

    List<DemoTrack> find
}
