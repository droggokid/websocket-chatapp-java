package com.websocket.application.controller;

import com.websocket.application.dto.Image;
import com.websocket.domain.service.ImageService;
import com.websocket.external.entity.ImageEntity;
import com.websocket.mapper.ImageMapper;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("api/image")
public class ImageController {
    private final ImageService imageService;

    public ImageController(ImageService imageService) {
        this.imageService = imageService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<byte[]> getImage(@PathVariable Long id) {
        try {
            ImageEntity image = imageService.getImageDataEntityById(id);

            if (image == null) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.valueOf("image/" + image.getType()));

            return new ResponseEntity<>(image.getImageData(), headers, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping
    public ResponseEntity<Map<String, Object>> saveImage(@RequestBody Image image) {
        try {
            ImageEntity imageEntity = ImageMapper.ImageDtoToImageEntity(image);
            ImageEntity savedEntity = imageService.uploadImage(imageEntity);
            return ResponseEntity.ok().body(Map.of("id", savedEntity.getId()));
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
