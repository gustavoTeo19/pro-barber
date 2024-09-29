package com.api.probarber.configs.aws;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;

import java.net.URI;

@Configuration
public class S3Config {
    @Value("${aws.region}")
    private String awsRegion;

//    @Bean
//    public AmazonS3 createS3Instance() {
//        return AmazonS3ClientBuilder.standard().withRegion(awsRegion).build();
//    }
    @Bean
    public S3Client createS3Instance() {
        return S3Client.builder()
                .region(Region.US_EAST_1)
                .forcePathStyle(true)
                .build();
    }

}
