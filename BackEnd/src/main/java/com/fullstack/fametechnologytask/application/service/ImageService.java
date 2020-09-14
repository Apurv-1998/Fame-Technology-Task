package com.fullstack.fametechnologytask.application.service;

import java.io.IOException;

import org.springframework.web.multipart.MultipartFile;

import com.fullstack.fametechnologytask.application.entity.ImageEntity;

public interface ImageService {

	ImageEntity uploadFile(MultipartFile file) throws IOException;

	ImageEntity retrieveImage(String imageId);

	boolean deleteImage(String imageId, String userId);

}
