package com.rmsoft.service;

import org.springframework.web.multipart.MultipartFile;

public interface FileUploadService {
	void uploadFile(MultipartFile file);
}
