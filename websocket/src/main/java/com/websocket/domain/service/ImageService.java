package com.websocket.domain.service;

import com.websocket.external.entity.ImageDataEntity;
import com.websocket.external.repository.ImageRepository;
import com.websocket.util.ImageCompressor;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;

;

@Service
public class ImageService {
    private final ImageRepository imageRepository;

    public ImageService(ImageRepository imageRepository) {
        this.imageRepository = imageRepository;
    }

    @Transactional
    public String uploadImage(MultipartFile file) throws IOException {
        imageRepository.save(ImageDataEntity.builder()
                .name(file.getOriginalFilename())
                .type(file.getContentType())
                .imageData(ImageCompressor.compressImage(file.getBytes()))
                .build());

        return "Image uploaded successfully: " + file.getOriginalFilename();
    }

    @Transactional
    public ImageDataEntity getImageDataEntityByName(String name) {
        Optional<ImageDataEntity> dbImage = imageRepository.findByName(name);

        return ImageDataEntity.builder()
                .name(dbImage.get().getName())
                .type(dbImage.get().getType())
                .imageData(ImageCompressor.decompressImage(dbImage.get().getImageData()))
                .build();
    }

    @Transactional
    public byte[] getImage(String name) {
        Optional<ImageDataEntity> dbImage = imageRepository.findByName(name);
        return ImageCompressor.decompressImage(dbImage.get().getImageData());
    }
}
