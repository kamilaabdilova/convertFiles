package org.example.convertfiles.controller;

import org.example.convertfiles.service.ConvertFileService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.http.MediaType;

import java.util.*;

@RestController
@RequestMapping("/convertFile")
public class ConvertFileController {
    private final ConvertFileService convertFileService;

    public ConvertFileController(ConvertFileService convertFileService) {
        this.convertFileService = convertFileService;
    }

    @PostMapping(value = "/multiple", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<Map<String, String>> convertMultipleFiles(
            @RequestPart List<MultipartFile> files,
            @RequestParam(defaultValue = "80") Integer quality) {

        try {
            Map<String, String> convertedFiles = convertFileService.convertMultipleFiles(files, quality);
            return ResponseEntity.ok(convertedFiles);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest()
                    .body(Map.of("error", e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.internalServerError()
                    .body(Map.of("error", "Ошибка сервера: " + e.getMessage()));
        }
    }
}