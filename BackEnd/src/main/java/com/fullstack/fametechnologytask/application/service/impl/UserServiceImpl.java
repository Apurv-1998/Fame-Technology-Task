package com.fullstack.fametechnologytask.application.service.impl;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fullstack.fametechnologytask.application.dto.CreateUserDto;
import com.fullstack.fametechnologytask.application.dto.LoginUserDto;
import com.fullstack.fametechnologytask.application.entity.ImageEntity;
import com.fullstack.fametechnologytask.application.entity.UserEntity;
import com.fullstack.fametechnologytask.application.entity.VerificationTokenEntity;
import com.fullstack.fametechnologytask.application.repository.UserRepository;
import com.fullstack.fametechnologytask.application.response.model.UserRest;
import com.fullstack.fametechnologytask.application.service.MailService;
import com.fullstack.fametechnologytask.application.service.TokenService;
import com.fullstack.fametechnologytask.application.service.UserService;
import com.fullstack.fametechnologytask.application.shared.EmailBody;
import com.fullstack.fametechnologytask.application.shared.Utils;


@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	ModelMapper mapper;
	
	@Autowired
	UserRepository userRepository;

	@Autowired
	Utils utils;
	
	@Autowired
	TokenService tokenService;
	
	@Autowired
	MailService mailService;
	
	
	@Override
	public UserEntity createNewUser(CreateUserDto userDto) {
		
		UserEntity user = mapper.map(userDto,UserEntity.class);
		
		user.setUserId(utils.generateUserId(5));
		user.setRegPassword(utils.generateUserId(10));
		
		UserEntity savedUser = userRepository.save(user);
		
		
		//Creation of Token
		VerificationTokenEntity token = tokenService.createVerificationToken(savedUser);
		
		//Sending the email
		mailService.sendMail(new EmailBody("Please Activate Your Email Account",savedUser.getEmail(),"Thank You for signing to the app\n"+" Click On the Below Link To Activate Your Account\n"
										   +" http://localhost:8080/fullstack-task/api/users/accountVerification/"+token.getTokenId()));
		
		
		return savedUser;
	}


	@Override
	public boolean verifyAccount(String tokenId) {
		
		boolean returnValue = false;
		
		UserEntity entity = tokenService.getUserDetailsFromToken(tokenId);
		
		entity.setVerified(true);
		
		UserEntity saved = userRepository.save(entity);
		
		returnValue = (saved!=null);
		
		tokenService.deleteToken(tokenId);
		
		return returnValue;
		
	}


	@Override
	public void addImage(String userId, ImageEntity image) {
		
		
		UserEntity user = userRepository.findUserByUserId(userId);
		
		if(user == null)
			throw new RuntimeException("Invalid User Details");
		
		user.setImageDetails(image);
		
		userRepository.save(user);
		
	}


	@Override
	public UserEntity loginUser(LoginUserDto loginDto) {
		
		List<UserEntity> users = userRepository.findUserByEmail(loginDto.getEmail());
		
		if(users.size()!=1)
			throw new RuntimeException("Multiple Users");
		
		UserEntity user = users.get(0);
		
		if(!user.getPassword().equals(loginDto.getPassword()))
			throw new RuntimeException("Passwords Donot Match");
		
		System.out.println("Login In User -> "+user);
		
		return user;
		
	}


	@Override
	public UserRest getUserDetails(String userId) {
		
		UserEntity user = userRepository.findUserByUserId(userId);
		
		if(user == null)
			throw new RuntimeException("Got No user");
		
		UserRest response = mapper.map(user,UserRest.class);
		
		response.setUserName(user.getFirstName()+" "+user.getLastName());
		response.setDuration(utils.getDuration(user));
		response.setUpdatedTime(utils.getUpdatedTime(user));
		if(user.getImageDetails()!=null)
			response.setImageId(user.getImageDetails().getImageId());
		
		return response;
	}


	@Override
	public UserEntity getUser(String userId) {
		
		return userRepository.findUserByUserId(userId);
		
	}


	@Override
	public void saveUser(UserEntity user) {
		
		userRepository.save(user);
		
	}

}
