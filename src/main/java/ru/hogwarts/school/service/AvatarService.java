package ru.hogwarts.school.service;

import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;
import ru.hogwarts.school.model.Avatar;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public interface AvatarService {

    ResponseEntity<Void> uploadAvatar(Long id, MultipartFile avatar) throws IOException;

    Avatar getAvatarById(Long id);

    ResponseEntity<byte[]> downloadFromDataBase(Long id);

    ResponseEntity<byte[]> downloadFromLocalDisk(Long id, HttpServletResponse response) throws IOException;

    ResponseEntity<byte[]> downloadAvatar(Long id, Enum src, HttpServletResponse response) throws IOException;
}
