package ru.hogwarts.school.service;

import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;
import ru.hogwarts.school.model.Avatar;
import ru.hogwarts.school.model.Src;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public interface AvatarService {

    void uploadAvatar(Long id, MultipartFile avatar) throws IOException;

    Avatar getAvatarById(Long id);

    ResponseEntity<byte[]> downloadAvatar(Long id, Src src, HttpServletResponse response) throws IOException;

    ResponseEntity<byte[]> downloadFromDataBase(Long id);

    ResponseEntity<byte[]> downloadFromLocalDisk(Long id, HttpServletResponse response) throws IOException;
}
