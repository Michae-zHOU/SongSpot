package com.songspot.server.controller.model;

public class CreateDemoTrack {

    private String filename;

    private String fileType;

    private String url;

    private byte[] data;

    private String artist;

    public CreateDemoTrack() {
    }

    public CreateDemoTrack(String filename, String fileType, String url, byte[] data, String artist) {
        this.filename = filename;
        this.fileType = fileType;
        this.url = url;
        this.data = data;
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

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
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
}