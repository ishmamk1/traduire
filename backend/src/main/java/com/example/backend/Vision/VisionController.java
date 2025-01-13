package com.example.backend.Vision;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Paths;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ResourceLoader;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


import okhttp3.*;

@RestController
@RequestMapping(path = "/api")
@CrossOrigin(origins = "http://localhost:5173")
public class VisionController {

    private final VisionService visionService;

    @Autowired
    public VisionController(VisionService visionService) {
        this.visionService = visionService;
    }

    @GetMapping("/call")
    public String getAPI() {
        return visionService.getApiEndpoint();
    }

    @PostMapping("/ocr")
    public String getTextFromImage(@RequestParam("file") MultipartFile file) {
        if (file.isEmpty()) {
            return "No file recieved";
        }

        try {
            String convertedText = visionService.processImage(file);
            System.out.println(convertedText);
            return convertedText;
        } catch (Exception e) {
            // Log the stack trace to get more detailed error information
            return "Error occurred while uploading the file: " + e.getMessage();
        }
    }

    @PostMapping("/image")
    public ResponseEntity<String> uploadImage(@RequestParam("file") MultipartFile file) {
        if (file.isEmpty()) {
            return ResponseEntity.badRequest().body("No file recieved");
        }

        try {
            return ResponseEntity.ok("File uploaded successfully: " + file.getOriginalFilename());
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error occurred while uploading the file");
        }
    }

}
