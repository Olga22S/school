package ru.hogwarts.school.controller;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.hogwarts.school.model.Avatar;

import ru.hogwarts.school.model.Src;
import ru.hogwarts.school.service.AvatarService;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/avatar")
public class AvatarController {

    private final AvatarService service;

    public AvatarController(AvatarService service) {
        this.service = service;
    }

    @PostMapping(value = "/{studentId}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Void> uploadAvatar(@PathVariable Long studentId, @RequestParam MultipartFile avatar) throws IOException {
        service.uploadAvatar(studentId, avatar);
        return ResponseEntity.ok(null);
    }

    @GetMapping("/preview/{id}")
    public ResponseEntity<byte[]> downloadAvatar(@PathVariable Long id, @RequestParam(defaultValue = "FILE") Src src,
                                                 HttpServletResponse response) throws IOException {
        return service.downloadAvatar(id, src, response);
    }

    @GetMapping
    public ResponseEntity<List<Avatar>> getAllAvatars(@RequestParam("page") Integer pageNumber,
                                                      @RequestParam("size") Integer pageSize) {
        List<Avatar> avatars = service.getAllAvatars(pageNumber, pageSize);
        return ResponseEntity.ok(avatars);
    }
}
