package server.songspotserver.repository.model;

import javax.persistence.*;

@Entity
@Table(name = "track_demo")
public class TrackDemo {

    @Id
    @GeneratedValue(generator = "track_demo_generator")
    @SequenceGenerator(
            name = "track_demo_generator",
            sequenceName = "track_demo_sequence",
            initialValue = 100
    )
    private Long id;

    private String fileName;

    private String fileType;

    @Lob
    private byte[] data;

    public TrackDemo() {
    }

    public TrackDemo(Long id, String fileName, String fileType, byte[] data) {
        this.id = id;
        this.fileName = fileName;
        this.fileType = fileType;
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
}
