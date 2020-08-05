package com.songspot.server.controller.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.google.gson.annotations.Expose;

import java.util.Objects;

public class DemoTrack {

    @Expose
    protected Long id;

    String filename;

    String fileType;

    String artist;

    public DemoTrack() {}

    public DemoTrack(Long id, String filename, String fileType, String artist) {
        this.id = id;
        this.filename = filename;
        this.fileType = fileType;
        this.artist = artist;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof DemoTrack)) return false;
        DemoTrack demoTrack = (DemoTrack) o;
        return Objects.equals(getId(), demoTrack.getId()) &&
                Objects.equals(getFilename(), demoTrack.getFilename()) &&
                Objects.equals(getFileType(), demoTrack.getFileType()) &&
                Objects.equals(getArtist(), demoTrack.getArtist());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getFilename(), getFileType(), getArtist());
    }
}