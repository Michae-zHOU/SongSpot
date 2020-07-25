package com.songspot.server.repository.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "demo_track_curator")
public class DemoTrackCurator {

    @EmbeddedId
    private DemoTrackCuratorKey id;

    @ManyToOne
    @MapsId("demo_track_id")
    @JoinColumn(name = "demo_track_id")
    private DemoTrack demoTrack;

    @ManyToOne
    @MapsId("curator_id")
    @JoinColumn(name = "curator_id")
    private  Curator curator;

    private boolean viewed;

    public DemoTrackCurator() {
    }

    public DemoTrackCurator(DemoTrackCuratorKey id, DemoTrack demoTrack, Curator curator) {
        this.id = id;
        this.demoTrack = demoTrack;
        this.curator = curator;
        this.viewed = false;
    }

    public DemoTrackCurator(DemoTrackCuratorKey id, DemoTrack demoTrack, Curator curator, boolean viewed) {
        this.id = id;
        this.demoTrack = demoTrack;
        this.curator = curator;
        this.viewed = viewed;
    }

    public DemoTrackCuratorKey getId() {
        return id;
    }

    public void setId(DemoTrackCuratorKey id) {
        this.id = id;
    }

    public DemoTrack getDemoTrack() {
        return demoTrack;
    }

    public void setDemoTrack(DemoTrack demoTrack) {
        this.demoTrack = demoTrack;
    }

    public Curator getCurator() {
        return curator;
    }

    public void setCurator(Curator curator) {
        this.curator = curator;
    }

    public boolean isViewed() {
        return viewed;
    }

    public void setViewed(boolean viewed) {
        this.viewed = viewed;
    }
}
