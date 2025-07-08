package com.websocket.external.repository;

import com.websocket.external.entity.ImageDataEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ImageRepository extends JpaRepository<ImageDataEntity, Long> {
    Optional<ImageDataEntity> findByName(String name);
}
