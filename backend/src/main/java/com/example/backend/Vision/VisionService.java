package com.example.backend.Vision;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.api.client.util.Value;
import com.google.cloud.vision.v1.Image;
import com.google.cloud.vision.v1.ImageAnnotatorClient;
import com.google.cloud.vision.v1.TextAnnotation;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Array;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Map;

import javax.print.attribute.standard.Media;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import okhttp3.*;

@Service
public class VisionService {

    private final VisionConfig visionConfig;

    @Value("${ocr-space.api.key}")
    private String API_KEY;

    @Value("${ocr-space.api.endpoint}")
    private String apiEndpoint;

    @Autowired
    public VisionService(VisionConfig visionConfig) {
        this.visionConfig = visionConfig;
    }

    public String getApiKey() {
        return visionConfig.getApiKey();
    }

    public String getApiEndpoint() {
        return visionConfig.getApiEndpoint();
    }

    public String processImage(MultipartFile imageFile) throws IOException {
        String API_KEY = visionConfig.getApiKey();

        String apiEndpoint = visionConfig.getApiEndpoint();
        

        System.out.println(apiEndpoint);

        MultipartBody.Builder requestBodyBuilder = new MultipartBody.Builder().setType(MultipartBody.FORM);

        requestBodyBuilder.addFormDataPart("file", imageFile.getOriginalFilename(), RequestBody.create(imageFile.getBytes(), MediaType.parse("image/jpeg")));
        requestBodyBuilder.addFormDataPart("apikey", API_KEY);

        Request request = new Request.Builder()
            .url(apiEndpoint)
            .post(requestBodyBuilder.build())
            .build();
        
        OkHttpClient client = new OkHttpClient();

        Response response = client.newCall(request).execute();
        
        ObjectMapper objectMapper = new ObjectMapper();

        JsonNode JsonResponse = objectMapper.readTree(response.body().string());

        String parsedText = JsonResponse
            .path("ParsedResults")
            .get(0)
            .path("ParsedText")
            .asText();
        return parsedText;
    }
}
