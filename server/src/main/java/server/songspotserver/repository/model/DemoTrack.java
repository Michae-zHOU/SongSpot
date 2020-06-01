package server.songspotserver.repository.model;

import com.sun.istack.NotNull;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "track_demo")
public class DemoTrack {

    @Id
    @GeneratedValue(generator = "track_demo_generator")
    @SequenceGenerator(
            name = "track_demo_generator",
            sequenceName = "track_demo_sequence",
            initialValue = 100
    )
    private Long id;

    @Column(name = "filename", nullable = false, updatable = true)
    private String fileName;

    @Column(name = "file_type", nullable = false, updatable = true)
    private String fileType;

    @Column(name = "artist", nullable = false, updatable = true)
    private String artist;

    @Column(name="curators")
    @ElementCollection(targetClass=String.class)
    private Set<String> curators = new HashSet<>();

    @Lob
    private byte[] data;

    public DemoTrack() {
    }

    public DemoTrack(Long id, String fileName, String fileType, String artist, List<String> curators, byte[] data) {
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

    public List<String> getCurators() {
        return curators;
    }

    public void setCurators(List<String> curators) {
        this.curators = curators;
    }
}
