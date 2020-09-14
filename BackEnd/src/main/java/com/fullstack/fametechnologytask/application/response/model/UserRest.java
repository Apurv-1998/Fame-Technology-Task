package com.fullstack.fametechnologytask.application.response.model;

import java.util.Date;

import lombok.Data;

@Data
public class UserRest {
	
	private String userId;
	private String userName;
	private String email;
	private Date dob;
	private String regPassword;
	private boolean isVerified;
	private String duration;
	private String imageId;
	private String updatedTime;

}
