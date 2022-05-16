package ru.hogwarts.school.controller;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.hogwarts.school.model.Src;
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
    public ResponseEntity<Void> uploadAvatar(@PathVariable Long studentId, @RequestParam MultipartFile avatar) throws IOException {
        return service.uploadAvatar(studentId, avatar);
    }

    @GetMapping("/preview/{id}")
    public ResponseEntity<byte[]> downloadAvatar(@PathVariable Long id, @RequestParam(defaultValue = "FILE") String src,
                                                 HttpServletResponse response) throws IOException {
        return service.downloadAvatar(id, Src.valueOf(src), response);
    }
}
