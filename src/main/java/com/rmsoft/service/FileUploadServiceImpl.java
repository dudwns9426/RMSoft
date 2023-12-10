package com.rmsoft.service;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FileUploadServiceImpl implements FileUploadService {

    @Value("${upload.path}")
    private String uploadPath;

    @Override
    public void uploadFile(MultipartFile file) {
        try {
        	String fileName = new String(file.getOriginalFilename().getBytes("8859_1"), "UTF-8");
            File dest = new File(uploadPath + fileName);
            file.transferTo(dest);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}