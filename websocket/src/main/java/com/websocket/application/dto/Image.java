package com.websocket.application.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Image {
    private String name;
    private String type;
    private byte[] imageData;
}
