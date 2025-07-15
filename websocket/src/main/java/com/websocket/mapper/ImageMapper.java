package com.websocket.mapper;

import com.websocket.application.dto.Image;
import com.websocket.external.entity.ImageEntity;

public class ImageMapper {
    public static ImageEntity ImageDtoToImageEntity(Image image) {
        if (image == null) {
            return null;
        }

        return ImageEntity.builder()
                .name(image.getName())
                .type(image.getType())
                .imageData(image.getImageData())
                .build();
    }

    public static Image ImageEntityToImageDto(ImageEntity imageEntity) {
        if (imageEntity == null) {
            return null;
        }

        return Image.builder()
                .name(imageEntity.getName())
                .type(imageEntity.getType())
                .imageData(imageEntity.getImageData())
                .build();
    }
}

