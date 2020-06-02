package com.songspot.server.repository.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "demo_track")
public class DemoTrack {

    @Id
    @GeneratedValue(generator = "demo_track_generator")
    @SequenceGenerator(
            name = "demo_track_generator",
            sequenceName = "demo_track_sequence",
            initialValue = 100
    )
    private Long id;

    @Column(name = "filename", nullable = false, updatable = true)
    private String fileName;

    @Column(name = "file_type", nullable = false, updatable = true)
    private String fileType;

    @Column(name = "artist", nullable = false, updatable = true)
    private String artist;

    @Column(name = "curators")
    @ElementCollection(targetClass = String.class)
    private Set<String> curators = new HashSet<>();

    @Lob
    private byte[] data;

    public DemoTrack() {
    }

    public DemoTrack(Long id, String fileName, String fileType, String artist, Set<String> curators, byte[] data) {
        this.id = id;
        this.fileName = fileName;
        this.fileType = fileType;
        this.artist = artist;
        this.curators = curators;
        this.data = data;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    public byte[] getData() {
        return data;
    }

    public void setData(byte[] data) {
        this.data = data;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public Set<String> getCurators() {
        return curators;
    }

    public void setCurators(Set<String> curators) {
        this.curators = curators;
    }

    public com.songspot.server.controller.model.DemoTrack toPresentationModel() {
        return new com.songspot.server.controller.model.DemoTrack(
                this.getId(),
                this.getFileName(),
                this.getFileType(),
                this.getArtist()
        );
    }
}
