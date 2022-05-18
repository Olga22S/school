package ru.hogwarts.school.model;

import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.Arrays;
import java.util.Objects;

@Entity
public class Avatar {

    @Id
    @GeneratedValue
    private Long id;
    private String filePath;
    private long fileSize;
    private String mediaType;
    private byte[] data;
    @OneToOne
    @JoinColumn(name = "student_id")
    private Student student;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public long getFileSize() {
        return fileSize;
    }

    public void setFileSize(long fileSize) {
        this.fileSize = fileSize;
    }

    public String getMediaType() {
        return mediaType;
    }

    public void setMediaType(String mediaType) {
        this.mediaType = mediaType;
    }

    public byte[] getData() {
        return data;
    }

    public void setData(byte[] data) {
        this.data = data;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) {
            return false;
        }
        Avatar avatar = (Avatar) o;
        return id != null && Objects.equals(id, avatar.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

    @Override
    public String toString() {
        return "Avatar{" +
                "id=" + id +
                ", filePath='" + filePath + '\'' +
                ", fileSize=" + fileSize +
                ", mediaType='" + mediaType + '\'' +
                ", data=" + Arrays.toString(data) +
                ", student=" + student +
                '}';
    }

    public static AvatarBuilder builder() {
        return new AvatarBuilder();
    }

    public static class AvatarBuilder {

        private Long id;
        private String filePath;
        private long fileSize;
        private String mediaType;
        private byte[] data;
        private Student student;

        private AvatarBuilder() {
        }

        public AvatarBuilder id(Long id) {
            this.id = id;
            return this;
        }

        public AvatarBuilder filePath(String filePath) {
            this.filePath = filePath;
            return this;
        }

        public AvatarBuilder fileSize(long fileSize) {
            this.fileSize = fileSize;
            return this;
        }

        public AvatarBuilder mediaType(String mediaType) {
            this.mediaType = mediaType;
            return this;
        }

        public AvatarBuilder data(byte[] data) {
            this.data = data;
            return this;
        }

        public AvatarBuilder student(Student student) {
            this.student = student;
            return this;
        }

        public Avatar build() {
            Avatar avatar = new Avatar();
            avatar.setId(id);
            avatar.setFilePath(filePath);
            avatar.setFileSize(fileSize);
            avatar.setMediaType(mediaType);
            avatar.setData(data);
            avatar.setStudent(student);
            return avatar;
        }
    }
}
