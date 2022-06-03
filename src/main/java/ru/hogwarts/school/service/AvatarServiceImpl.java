package ru.hogwarts.school.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.hogwarts.school.model.Avatar;
import ru.hogwarts.school.model.Src;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.repository.AvatarRepository;

import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import static java.nio.file.StandardOpenOption.CREATE_NEW;

@Service
@Transactional
public class AvatarServiceImpl implements AvatarService {

    private final AvatarRepository repository;
    private final StudentService studentService;
    private final Logger logger = LoggerFactory.getLogger(AvatarServiceImpl.class);

    @Value("${path.to.avatars.folder}")
    private String avatarsDir;

    public AvatarServiceImpl(AvatarRepository repository, StudentService studentService) {
        this.repository = repository;
        this.studentService = studentService;
    }

    @Override
    public void uploadAvatar(Long id, MultipartFile avatar) throws IOException {
        logger.info("Was invoked method for upload avatar");
        Student student = studentService.get(id);
        Path filePath = Path.of(avatarsDir, id + "." + getExtension(avatar.getOriginalFilename()));
        Files.createDirectories(filePath.getParent());
        Files.deleteIfExists(filePath);
        try (InputStream is = avatar.getInputStream();
             OutputStream os = Files.newOutputStream(filePath, CREATE_NEW);
             BufferedInputStream bis = new BufferedInputStream(is, 1024);
             BufferedOutputStream bos = new BufferedOutputStream(os, 1024)) {
            bis.transferTo(bos);
        }
        Avatar studentAvatar = findByStudentId(id);
        studentAvatar.setStudent(student);
        studentAvatar.setFilePath(filePath.toString());
        studentAvatar.setFileSize(avatar.getSize());
        studentAvatar.setMediaType(avatar.getContentType());
        studentAvatar.setData(avatar.getBytes());
        repository.save(studentAvatar);
    }

    @Override
    public Avatar getAvatarById(Long id) {
        logger.info("Was invoked method to get avatar by id={}", id);
        return repository.findById(id).get();
    }

    @Override
    public ResponseEntity<byte[]> downloadFromDataBase(Long id) {
        logger.info("Download avatar from db");
        Avatar avatar = getAvatarById(id);
        return ResponseEntity.status(HttpStatus.OK)
                .contentLength(avatar.getData().length)
                .contentType(MediaType.parseMediaType(avatar.getMediaType()))
                .body(avatar.getData());
    }

    @Override
    public ResponseEntity<byte[]> downloadFromLocalDisk(Long id, HttpServletResponse response) throws IOException {
        logger.info("Download avatar from local disk");
        Avatar avatar = getAvatarById(id);
        Path path = Path.of(avatar.getFilePath());
        try (InputStream is = Files.newInputStream(path);
             OutputStream os = response.getOutputStream()) {
            response.setStatus(200);
            response.setContentType(avatar.getMediaType());
            response.setContentLength((int) avatar.getFileSize());
            is.transferTo(os);
        }
        return new ResponseEntity(response, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<byte[]> downloadAvatar(Long id, Src src, HttpServletResponse response) throws IOException {
        logger.info("Was invoked method for download avatar");
        if (src == Src.DB) {
            return downloadFromDataBase(id);
        }
        return downloadFromLocalDisk(id, response);
    }

    @Override
    public List<Avatar> getAllAvatars(Integer pageNumber, Integer pageSize) {
        logger.info("Was invoked method for getting all avatars");
        PageRequest pageRequest = PageRequest.of(pageNumber - 1, pageSize);
        return repository.findAll(pageRequest).getContent();
    }

    private Avatar findByStudentId(Long id) {
        return repository.findByStudentId(id)
                .orElse(new Avatar());
    }

    private String getExtension(String fileName) {
        return fileName.substring(fileName.lastIndexOf(".") + 1);
    }
}
