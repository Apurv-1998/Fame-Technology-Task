package com.fullstack.fametechnologytask.application.request.model;

import java.util.Date;

import lombok.Data;

@Data
public class CreateUserDetailsModel {
	
	private String firstName;
	private String lastName;
	private String email;
	private String password;
	private Date dob;

}
