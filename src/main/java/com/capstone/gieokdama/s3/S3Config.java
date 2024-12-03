package com.capstone.gieokdama.s3;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;

@Configuration
public class S3Config {

    @Bean
    public S3Client s3Client() {
        return S3Client.builder()
                .region(Region.of("ap-northeast-2")) // 리전을 서울로 설정
                .credentialsProvider(StaticCredentialsProvider.create(
                        AwsBasicCredentials.create(
                                "AKIAWAA66CSU262NHTGE",      // IAM에서 받은 Access Key
                                "36AmQ0Bk0yJG6guv4ONxwssG3jywm8Xfii4jB+bn"       // IAM에서 받은 Secret Key
                        )
                ))
                .build();
    }
}
