package com.clone.youtubeclone.service.impl;


import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.clone.youtubeclone.service.FileService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.util.UUID;


@Service
@RequiredArgsConstructor
public class S3ServiceImpl implements FileService {


    public static final String AASHISH_YOUTUBE = "aashish-youtube";

    private final AmazonS3Client amazonS3Client;


    @Override
    public String uploadFile(MultipartFile multipartFile) {


        var fileName = StringUtils.getFilenameExtension(multipartFile.getOriginalFilename());

        var key = UUID.randomUUID().toString() + "." +fileName;
        var metaData = new ObjectMetadata();
        metaData.setContentLength(multipartFile.getSize());
        metaData.setContentType(multipartFile.getContentType());
        try {
            amazonS3Client.putObject(AASHISH_YOUTUBE, key, multipartFile.getInputStream(), metaData);

        } catch (IOException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "exception occuered while uploading");
        }

        amazonS3Client.setObjectAcl(AASHISH_YOUTUBE, key, CannedAccessControlList.PublicRead);
        return amazonS3Client.getResourceUrl(AASHISH_YOUTUBE, key);

    }
}

