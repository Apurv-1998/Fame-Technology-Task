package com.fullstack.fametechnologytask.application.service.impl;

import java.io.IOException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.fullstack.fametechnologytask.application.entity.ImageEntity;
import com.fullstack.fametechnologytask.application.entity.UserEntity;
import com.fullstack.fametechnologytask.application.repository.ImageRepository;
import com.fullstack.fametechnologytask.application.service.ImageService;
import com.fullstack.fametechnologytask.application.service.UserService;
import com.fullstack.fametechnologytask.application.shared.Utils;

@Service
public class ImageServiceImpl implements ImageService {
	
	@Autowired
	ImageRepository imageRepository;
	
	@Autowired
	Utils utils;
	
	@Autowired
	UserService userService;

	@Override
	public ImageEntity uploadFile(MultipartFile file) throws IOException {
		
		ImageEntity image = new ImageEntity();
		
		image.setImageId(utils.generateUserId(8));
		image.setImageName(file.getOriginalFilename());
		image.setType(file.getContentType());
		image.setPicByte(utils.compressImage(file.getBytes()));
		
		return imageRepository.save(image);
		
		
	}

	@Override
	public ImageEntity retrieveImage(String imageId) {
		
		final Optional<ImageEntity> optionalImage = imageRepository.findImageByImageId(imageId);
		
		ImageEntity image = new ImageEntity();
		if(optionalImage.get()!=null) {
			image.setImageId(optionalImage.get().getImageId());
			image.setImageName(optionalImage.get().getImageName());
			image.setType(optionalImage.get().getType());
			image.setPicByte(utils.decompressImage(optionalImage.get().getPicByte()));
		}
		
		return image;
		
	}

	@Override
	public boolean deleteImage(String imageId,String userId) {
		
		UserEntity user =  userService.getUser(userId);
		
		if(user == null)
			return false;
		
		Optional<ImageEntity> optionalImage = imageRepository.findImageByImageId(imageId);
		
		ImageEntity image = optionalImage.get();
		
		if(image == null || !user.getImageDetails().equals(image))
			return false;
		
		user.setImageDetails(null);
		userService.saveUser(user);
		
		imageRepository.delete(image);
		
		return true;
		
	}

}
