package com.fullstack.fametechnologytask.application.service;

import com.fullstack.fametechnologytask.application.dto.CreateUserDto;
import com.fullstack.fametechnologytask.application.dto.LoginUserDto;
import com.fullstack.fametechnologytask.application.entity.ImageEntity;
import com.fullstack.fametechnologytask.application.entity.UserEntity;
import com.fullstack.fametechnologytask.application.response.model.UserRest;

public interface UserService {

	UserEntity createNewUser(CreateUserDto userDto);

	boolean verifyAccount(String tokenId);

	void addImage(String userId, ImageEntity image);

	UserEntity loginUser(LoginUserDto loginDto);

	UserRest getUserDetails(String userId);

	UserEntity getUser(String userId);

	void saveUser(UserEntity user);

}
