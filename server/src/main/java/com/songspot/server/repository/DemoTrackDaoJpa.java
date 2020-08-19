package com.songspot.server.repository;

import com.songspot.server.controller.model.CreateDemoTrack;
import com.songspot.server.exception.ResourceNotFoundException;
import com.songspot.server.repository.model.Artist;
import com.songspot.server.repository.model.Curator;
import com.songspot.server.repository.model.DemoTrack;
import com.songspot.server.repository.model.DemoTrackCurator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class DemoTrackDaoJpa {

    @Autowired
    private DemoTrackRepository demoTrackRepository;

    @Autowired
    private DemoTrackCuratorRepository demoTrackCuratorRepository;

    @Autowired
    private ArtistRepository artistRepository;

    @Autowired
    private CuratorRepository curatorRepository;

    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
    public com.songspot.server.controller.model.DemoTrack createDemoTrack(String requester, CreateDemoTrack createDemoTrack) {
        DemoTrack demoTrack = new DemoTrack();
        demoTrack.setArtist(createDemoTrack.getArtist());
        demoTrack.setData(createDemoTrack.getData());
        demoTrack.setFileName(createDemoTrack.getFilename());
        demoTrack.setFileType(createDemoTrack.getFileType());
        demoTrack.setCreatedAt(Timestamp.valueOf(LocalDateTime.now()));
        demoTrack.setUpdatedAt(Timestamp.valueOf(LocalDateTime.now()));

        this.demoTrackRepository.save(demoTrack);
        return demoTrack.toPresentationModel();
    }

    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
    public com.songspot.server.controller.model.DemoTrack submit(String requester, Long trackId, String curatorName) {
        DemoTrack demoTrack = this.demoTrackRepository.findById(trackId)
                .orElseThrow(() -> new ResourceNotFoundException("Track by " + trackId + " not found"));

        /*if(!requester.equals(demoTrack.getArtist())) {
            throw new IllegalStateException("Requester is not artist");
        }*/
        Curator curator = this.curatorRepository.findByName(curatorName)
                .orElseThrow(() -> new ResourceNotFoundException("Curator with name " + curatorName + " not found"));

        demoTrack.setUpdatedAt(Timestamp.valueOf(LocalDateTime.now()));

        // Create a join relationship of DemoTrack and Curator
        DemoTrackCurator demoTrackCurator = new DemoTrackCurator();
        demoTrackCurator.setId(demoTrack.getId(), curator.getId());
        demoTrackCurator.setDemoTrack(demoTrack);
        demoTrackCurator.setCurator(curator);

        this.demoTrackRepository.save(demoTrack);
        this.demoTrackCuratorRepository.save(demoTrackCurator);

        return demoTrack.toPresentationModel();
    }

    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
    public List<com.songspot.server.controller.model.DemoTrack> getDemoTrackOfArtist(String artistName) {
        Artist artist = this.artistRepository.findByName(artistName)
                .orElseThrow(() -> new ResourceNotFoundException("Artist with name " + artistName + " not found"));

        return this.demoTrackRepository.findAllByArtist(artistName)
                .stream().map(DemoTrack::toPresentationModel).collect(Collectors.toList());
    }

    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
    public List<com.songspot.server.controller.model.DemoTrack> getDemoTrackOfCurator(String curatorName) {
        Curator curator = this.curatorRepository.findByName(curatorName)
                .orElseThrow(() -> new ResourceNotFoundException("Curator with name " + curatorName + " not found"));
        List<Long> demoTrackIds = this.demoTrackCuratorRepository
                .findAllByCuratorId(curator.getId()).stream().map(t -> t.getDemoTrack().getId())
                .collect(Collectors.toList());

        return this.demoTrackRepository.findAllById(demoTrackIds)
                .stream().map(DemoTrack::toPresentationModel).collect(Collectors.toList());
    }

    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
    public List<com.songspot.server.controller.model.DemoTrack> getUnViewedDemoTrackOfCurator(String curatorName) {
        Curator curator = this.curatorRepository.findByName(curatorName)
                .orElseThrow(() -> new ResourceNotFoundException("Curator with name " + curatorName + " not found"));
        List<Long> demoTrackIds = this.demoTrackCuratorRepository
                .findAllByCuratorIdAndViewed(curator.getId(), false).stream().map(t -> t.getDemoTrack().getId())
                .collect(Collectors.toList());

        return this.demoTrackRepository.findAllById(demoTrackIds)
                .stream().map(DemoTrack::toPresentationModel).collect(Collectors.toList());
    }

    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
    public List<com.songspot.server.controller.model.DemoTrack> getViewedDemoTrackOfCurator(String curatorName) {
        Curator curator = this.curatorRepository.findByName(curatorName)
                .orElseThrow(() -> new ResourceNotFoundException("Curator with name " + curatorName + " not found"));
        List<Long> demoTrackIds = this.demoTrackCuratorRepository
                .findAllByCuratorIdAndViewed(curator.getId(), true).stream().map(t -> t.getDemoTrack().getId())
                .collect(Collectors.toList());

        return this.demoTrackRepository.findAllById(demoTrackIds)
                .stream().map(DemoTrack::toPresentationModel).collect(Collectors.toList());
    }
}
