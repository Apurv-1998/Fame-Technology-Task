package com.fullstack.fametechnologytask.application.response.model;

import lombok.Data;

@Data
public class ImageRest {
	
	private String imageId;
	private String imageName;
	private String type;
	private byte[] picByte;
}
