package org.example.convertfiles.service;

import io.github.mojtabaJ.cwebp.WebpConverter;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Base64;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ConvertFileService {

    public Map<String, String> convertMultipleFiles(List<MultipartFile> files, Integer quality) throws IOException {
        // Проверка качества
        if (quality < 10 || quality > 100) {
            throw new IllegalArgumentException("Качество должно быть в диапазоне от 10 до 100");
        }

        // Проверяем, что файлы не пусты
        if (files == null || files.isEmpty()) {
            throw new IllegalArgumentException("Не переданы файлы для конвертации");
        }

        // Обрабатываем каждый файл
        Map<String, String> convertedFiles = new HashMap<>();
        for (MultipartFile file : files) {
            try {
                byte[] imageByteArray = file.getBytes();
                byte[] webpByte = WebpConverter.imageByteToWebpByte(imageByteArray, quality);
                String webpBase64 = Base64.getEncoder().encodeToString(webpByte);

                // В ответ добавляем оригинальное имя файла и закодированное изображение
                convertedFiles.put(file.getOriginalFilename(), webpBase64);
            } catch (Exception e) {
                convertedFiles.put(file.getOriginalFilename(), "Ошибка конвертации: " + e.getMessage());
            }
        }

        return convertedFiles;
    }
}