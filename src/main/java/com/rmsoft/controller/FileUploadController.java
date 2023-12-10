package com.rmsoft.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.rmsoft.service.FileUploadService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class FileUploadController {
   
    private final FileUploadService fileUploadService;

    @GetMapping("/file")
    public String showUploadForm() {
        return "upload_form.html";
    }

    @PostMapping("/file")
    public String handleFileUpload(@RequestParam("file") MultipartFile file) {
        fileUploadService.uploadFile(file);
        return "redirect:/success";
    }
    
    @GetMapping("/success")
    public String showUploadSuccessPage() {
        return "success.html";
    }
}
