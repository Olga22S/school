package ru.hogwarts.school.model;

public class AvatarBuilder {

    private Avatar avatar;

    public AvatarBuilder(Avatar avatar) {
        this.avatar = avatar;
    }

    public AvatarBuilder setFilePath(String filePath) {
        avatar.setFilePath(filePath);
        return this;
    }

    public AvatarBuilder setFileSize(Long fileSize) {
        avatar.setFileSize(fileSize);
        return this;
    }

    public AvatarBuilder setMediaType(String mediaType) {
        avatar.setMediaType(mediaType);
        return this;
    }

    public AvatarBuilder setData(byte[] data) {
        avatar.setData(data);
        return this;
    }

    public AvatarBuilder setStudent(Student student) {
        avatar.setStudent(student);
        return this;
    }
}
