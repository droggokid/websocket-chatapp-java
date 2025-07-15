package com.websocket.domain.service;

import com.websocket.external.entity.ImageEntity;
import com.websocket.external.repository.ImageRepository;
import com.websocket.util.ImageCompressor;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ImageService {
    private final ImageRepository imageRepository;

    public ImageService(ImageRepository imageRepository) {
        this.imageRepository = imageRepository;
    }

    @Transactional
    public ImageEntity uploadImage(ImageEntity imageEntity) {
        imageEntity.setImageData(ImageCompressor.compressImage(imageEntity.getImageData()));
        return imageRepository.save(imageEntity);
    }

    @Transactional
    public ImageEntity getImageDataEntityById(Long imageId) {
        Optional<ImageEntity> dbImage = imageRepository.findById(imageId);

        if (dbImage.isEmpty()) {
            throw new RuntimeException("Image not found");
        }

        return ImageEntity.builder()
                .name(dbImage.get().getName())
                .type(dbImage.get().getType())
                .imageData(ImageCompressor.decompressImage(dbImage.get().getImageData()))
                .build();
    }

    @Transactional
    public byte[] getImage(Long imageId) throws RuntimeException {
        Optional<ImageEntity> dbImage = imageRepository.findById(imageId);

        if (dbImage.isEmpty()) {
            throw new RuntimeException("Image not found with id: " + imageId);
        }

        return ImageCompressor.decompressImage(dbImage.get().getImageData());
    }
}
