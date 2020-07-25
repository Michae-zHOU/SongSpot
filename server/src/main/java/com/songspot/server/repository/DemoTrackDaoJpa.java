package com.songspot.server.repository;

import com.songspot.server.controller.model.CreateDemoTrack;
import com.songspot.server.exception.ResourceNotFoundException;
import com.songspot.server.repository.model.Curator;
import com.songspot.server.repository.model.DemoTrack;
import com.songspot.server.repository.model.DemoTrackCurator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Repository
public class DemoTrackDaoJpa {

    @Autowired
    private DemoTrackRepository demoTrackRepository;

    @Autowired
    private DemoTrackCuratorRepository demoTrackCuratorRepository;

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

    public List<com.songspot.server.controller.model.DemoTrack> getTracks(String requester) {
        // TODO
        return Collections.emptyList();
    }

}
