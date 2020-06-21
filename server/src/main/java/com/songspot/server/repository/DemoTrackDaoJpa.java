package com.songspot.server.repository;

import com.songspot.server.controller.model.CreateDemoTrack;
import com.songspot.server.exception.ResourceNotFoundException;
import com.songspot.server.repository.model.DemoTrack;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Repository
public class DemoTrackDaoJpa {

    @Autowired
    private DemoTrackRepository demoTrackRepository;

    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
    public com.songspot.server.controller.model.DemoTrack createDemoTrack(String requester, CreateDemoTrack createDemoTrack) {
        DemoTrack demoTrack = new DemoTrack();
        demoTrack.setArtist(createDemoTrack.getArtist());
        demoTrack.setCurators(new HashSet<>());
        demoTrack.setData(createDemoTrack.getData());
        demoTrack.setFileName(createDemoTrack.getFilename());
        demoTrack.setFileType(createDemoTrack.getFileType());
        demoTrack.setCreatedAt(Timestamp.valueOf(LocalDateTime.now()));
        demoTrack.setUpdatedAt(Timestamp.valueOf(LocalDateTime.now()));

        this.demoTrackRepository.save(demoTrack);
        return demoTrack.toPresentationModel();
    }

    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
    public com.songspot.server.controller.model.DemoTrack submit(String requester, Long trackId, String curator) {
        DemoTrack demoTrack = demoTrackRepository.findById(trackId)
                .orElseThrow(() -> new ResourceNotFoundException("Track by " + trackId + " not found"));
        Set<String> curators = demoTrack.getCurators();
        curators.add(curator);
        demoTrack.setCurators(curators);
        demoTrack.setUpdatedAt(Timestamp.valueOf(LocalDateTime.now()));

        this.demoTrackRepository.save(demoTrack);
        return demoTrack.toPresentationModel();
    }

}
