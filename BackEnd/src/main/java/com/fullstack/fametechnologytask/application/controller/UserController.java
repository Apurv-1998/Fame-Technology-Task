package com.fullstack.fametechnologytask.application.controller;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fullstack.fametechnologytask.application.dto.CreateUserDto;
import com.fullstack.fametechnologytask.application.dto.LoginUserDto;
import com.fullstack.fametechnologytask.application.entity.UserEntity;
import com.fullstack.fametechnologytask.application.request.model.CreateUserDetailsModel;
import com.fullstack.fametechnologytask.application.request.model.LoginRequestModel;
import com.fullstack.fametechnologytask.application.response.model.UserRest;
import com.fullstack.fametechnologytask.application.service.UserService;
import com.fullstack.fametechnologytask.application.shared.Utils;


@RestController
@RequestMapping("/api/users")
public class UserController {
	
	@Autowired
	ModelMapper mapper;
	
	@Autowired
	UserService userService;
	
	@Autowired
	Utils utils;
	
	
	@PostMapping(path = "/createUser",produces = {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE})
	public ResponseEntity<UserRest> createUser(@RequestBody CreateUserDetailsModel createUserDetailsModel) {
		
		CreateUserDto userDto = mapper.map(createUserDetailsModel,CreateUserDto.class);
		
		UserEntity user = userService.createNewUser(userDto);
		
		UserRest response = mapper.map(user,UserRest.class);
		
		response.setUserName(user.getFirstName()+" "+user.getLastName());
		response.setDuration(utils.getDuration(user));
		if(user.getImageDetails()!=null)
			response.setImageId(user.getImageDetails().getImageId());
		response.setUpdatedTime(utils.getUpdatedTime(user));
		
		return new ResponseEntity<UserRest>(response,HttpStatus.CREATED);
	}
	
	
	@PostMapping(path = "/login",produces = {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE})
	public ResponseEntity<UserRest> loginUser(@RequestBody LoginRequestModel loginRequestModel) {
		
		LoginUserDto loginDto = mapper.map(loginRequestModel,LoginUserDto.class);
		
		UserEntity user =  userService.loginUser(loginDto);
		
		UserRest response = mapper.map(user,UserRest.class);
		
		response.setUserName(user.getFirstName()+" "+user.getLastName());
		response.setDuration(utils.getDuration(user));
		response.setUpdatedTime(utils.getUpdatedTime(user));
		if(user.getImageDetails()!=null)
			response.setImageId(user.getImageDetails().getImageId());
		else
			response.setImageId(null);
		
		return new ResponseEntity<UserRest>(response,HttpStatus.OK);
		
	}
	
	@GetMapping(path = "/accountVerification/{tokenId}",produces = {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE})
	public ResponseEntity<String> verifyAccount(@PathVariable String tokenId) {
		
		
		boolean verified = userService.verifyAccount(tokenId);
		
		if(!verified)
			return new ResponseEntity<String>("Account Not Verified",HttpStatus.NOT_ACCEPTABLE);
		
		return new ResponseEntity<String>("Account Verification SuccessFul",HttpStatus.OK);
		
	}
	
	
	@GetMapping(path = "/{userId}/showDetails",produces = {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE})
	public ResponseEntity<UserRest> getUserDetails(@PathVariable String userId) {
		
		UserRest response = userService.getUserDetails(userId);
		
		return new ResponseEntity<UserRest>(response,HttpStatus.OK);
		
	}
}
