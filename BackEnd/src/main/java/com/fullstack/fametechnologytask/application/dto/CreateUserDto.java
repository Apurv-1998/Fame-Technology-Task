package com.fullstack.fametechnologytask.application.dto;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;

@Data
public class CreateUserDto implements Serializable {
	
	private static final long serialVersionUID = -2413399193573578416L;
	
	private String userId;
	private String firstName;
	private String lastName;
	private String password;
	private String regPassword;
	private Date dob;
	private String email;

}
