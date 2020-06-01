package server.songspotserver.controller.model;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class DemoTrack {

    String filename;

    String filetype;

    String artist;

    byte[] data;

    public DemoTrack() {}

    public DemoTrack(String filename, String filetype, String artist, byte[] data) {
        this.filename = filename;
        this.filetype = filetype;
        this.artist = artist;
        this.data = data;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public String getFiletype() {
        return filetype;
    }

    public void setFiletype(String filetype) {
        this.filetype = filetype;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public byte[] getData() {
        return data;
    }

    public void setData(byte[] data) {
        this.data = data;
    }
}
