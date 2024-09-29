package com.api.probarber.services;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.PutObjectResponse;
import software.amazon.awssdk.transfer.s3.model.UploadFileRequest;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.Objects;
import java.util.UUID;

@Log4j2
@Service
public class ImageUploadService {
    @Value("${aws.bucket.name}")
    private String bucketName;
//    @Autowired
//    private AmazonS3 amazonS3;

    @Autowired
    private S3Client s3Client;
    public String uploadImg(MultipartFile multipartFile){
        String fileName = UUID.randomUUID() + "-" + multipartFile.getOriginalFilename();
        try{
//            File file = this.convertMultipartFile(multipartFile);
//            amazonS3.putObject(bucketName, fileName, file);
//            s3Client.put
//            file.delete();

            File file2 = this.convertMultipartFile(multipartFile);
            PutObjectResponse response = s3Client.putObject(b -> b
                            .bucket(bucketName)
                            .key(fileName),
                    file2.toPath());

            return response.eTag();
//            return amazonS3.getUrl(bucketName, fileName).toString();


        } catch (Exception e){
            log.error(e);
            return null;
        }
    }
    private File convertMultipartFile(MultipartFile multipartFile) throws IOException {
        File convFile = new File(Objects.requireNonNull(multipartFile.getOriginalFilename()));
        FileOutputStream fos = new FileOutputStream(convFile);
        fos.write(multipartFile.getBytes());
        fos.close();
        return convFile;
    }
}
