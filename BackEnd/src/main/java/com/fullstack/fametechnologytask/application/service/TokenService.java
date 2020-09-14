package com.fullstack.fametechnologytask.application.service;

import com.fullstack.fametechnologytask.application.entity.UserEntity;
import com.fullstack.fametechnologytask.application.entity.VerificationTokenEntity;

public interface TokenService {

	VerificationTokenEntity createVerificationToken(UserEntity savedUser);

	UserEntity getUserDetailsFromToken(String tokenId);

	void deleteToken(String tokenId);

}
