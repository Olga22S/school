package ru.hogwarts.school.controller;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.hogwarts.school.service.AvatarService;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
@RequestMapping("/avatar")
public class AvatarController {

    private final AvatarService service;

    public AvatarController(AvatarService service) {
        this.service = service;
    }

    @PostMapping(value = "/{studentId}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<String> uploadAvatar(@PathVariable Long studentId, @RequestParam MultipartFile avatar) throws IOException {
        service.uploadAvatar(studentId, avatar);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/preview-from-db/{id}")
    public ResponseEntity<byte[]> downloadFromDataBase(@PathVariable Long id) {
        return service.downloadFromDataBase(id);
    }

    @GetMapping("/preview-from-local/{id}")
    public void downloadFromLocalDisk(@PathVariable Long id, HttpServletResponse response) throws IOException {
        service.downloadFromLocalDisk(id, response);
    }
}
