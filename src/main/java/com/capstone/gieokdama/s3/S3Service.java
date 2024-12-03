package com.capstone.gieokdama.s3;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;
import software.amazon.awssdk.core.exception.SdkException;

import java.io.IOException;
import java.io.InputStream;

@Service
public class S3Service {

    @Autowired
    private S3Client s3Client;

    public String uploadFile(MultipartFile file, String bucketName) {
        String fileName = System.currentTimeMillis() + "-" + file.getOriginalFilename();

        try (InputStream inputStream = file.getInputStream()) { // MultipartFile에서 InputStream 얻기
            s3Client.putObject(
                    PutObjectRequest.builder()
                            .bucket(bucketName)
                            .key(fileName)
                            .build(),
                    software.amazon.awssdk.core.sync.RequestBody.fromInputStream(inputStream, file.getSize()) // InputStream을 사용하여 업로드
            );
            return "https://%s.s3.%s.amazonaws.com/%s".formatted(bucketName, "ap-northeast-2", fileName);
        } catch (SdkException | IOException e) {
            throw new RuntimeException("File upload failed: " + e.getMessage(), e);
        }
    }
}
