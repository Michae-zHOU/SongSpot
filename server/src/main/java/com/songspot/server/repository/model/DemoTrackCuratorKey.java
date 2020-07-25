package com.songspot.server.repository.model;

import com.sun.istack.NotNull;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class DemoTrackCuratorKey implements Serializable {

    @NotNull
    @Column(name = "demo_track_id")
    private Long demoTrackId;

    @NotNull
    @Column(name = "curator_id")
    private Long curatorId;

    public DemoTrackCuratorKey() {
    }

    public DemoTrackCuratorKey(Long demoTrackId, Long curatorId) {
        this.demoTrackId = demoTrackId;
        this.curatorId = curatorId;
    }

    public Long getDemoTrackId() {
        return demoTrackId;
    }

    public void setDemoTrackId(Long demoTrackId) {
        this.demoTrackId = demoTrackId;
    }

    public Long getCuratorId() {
        return curatorId;
    }

    public void setCuratorId(Long curatorId) {
        this.curatorId = curatorId;
    }
}
