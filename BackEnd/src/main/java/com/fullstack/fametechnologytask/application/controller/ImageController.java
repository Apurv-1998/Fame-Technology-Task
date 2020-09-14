package com.fullstack.fametechnologytask.application.controller;

import java.io.IOException;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fullstack.fametechnologytask.application.entity.ImageEntity;
import com.fullstack.fametechnologytask.application.response.model.ImageRest;
import com.fullstack.fametechnologytask.application.service.ImageService;
import com.fullstack.fametechnologytask.application.service.UserService;

@RestController
@RequestMapping("/api/images")
public class ImageController {
	
	@Autowired
	ModelMapper mapper;
	
	@Autowired
	ImageService imageService;
	
	@Autowired
	UserService userService;
	
	@PostMapping(path = "/{userId}/upload",produces = {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE})
	public ResponseEntity<ImageRest> uploadImage(@PathVariable String userId,@RequestParam(name = "file") MultipartFile file) throws IOException {
		
		ImageEntity image = imageService.uploadFile(file);
		
		
		userService.addImage(userId,image);
		
		ImageRest response = mapper.map(image,ImageRest.class);
		
		return new ResponseEntity<ImageRest>(response,HttpStatus.CREATED);
		
		
	}
	
	@GetMapping(path = "/retrieveImage/{imageId}",produces = {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE})
	public ResponseEntity<ImageRest> retrieveImage(@PathVariable String imageId) {
		
		ImageEntity image = imageService.retrieveImage(imageId);
		
		ImageRest response = mapper.map(image,ImageRest.class);
		
		return new ResponseEntity<ImageRest>(response,HttpStatus.OK);
		
	}
	
	
	@DeleteMapping(path = "/{userId}/{imageId}/deleteImage",produces = {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE})
	public ResponseEntity<Boolean> deleteImage(@PathVariable String imageId,@PathVariable String userId) {
		
		boolean imageFound = imageService.deleteImage(imageId,userId);
		
		return new ResponseEntity<Boolean>(imageFound,HttpStatus.INTERNAL_SERVER_ERROR);
		
	}

}
