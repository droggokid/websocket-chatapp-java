package com.websocket.util;

import com.websocket.external.entity.ImageDataEntity;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.zip.Deflater;
import java.util.zip.Inflater;

public class ImageCompressor {

    public static byte[] compressImage(byte[] imageData) {
        Deflater deflater = new Deflater();
        deflater.setLevel(Deflater.BEST_COMPRESSION);
        deflater.setInput(imageData);
        deflater.finish();

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        byte[] buffer = new byte[4*1024];
        while (!deflater.finished()) {
            int count = deflater.deflate(buffer);
            outputStream.write(buffer, 0, count);
        }
        try {
            outputStream.close();
        } catch (Exception e) {
            throw new RuntimeException("Failed to compress image: " + e);
        }
        return outputStream.toByteArray();
    }

    public static byte[] decompressImage(byte[] imageData) {
        Inflater inflater = new Inflater();
        inflater.setInput(imageData);
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        byte[] buffer = new byte[4*1024];
        try {
            while (!inflater.finished()) {
                int count = inflater.inflate(buffer);
                outputStream.write(buffer, 0, count);
            }
            outputStream.close();
        } catch (Exception e) {
            throw new RuntimeException("Failed to decompress image: " + e);
        }
        return outputStream.toByteArray();
    }
}
