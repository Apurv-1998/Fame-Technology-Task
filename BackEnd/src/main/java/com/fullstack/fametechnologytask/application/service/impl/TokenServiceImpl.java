package com.fullstack.fametechnologytask.application.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fullstack.fametechnologytask.application.entity.UserEntity;
import com.fullstack.fametechnologytask.application.entity.VerificationTokenEntity;
import com.fullstack.fametechnologytask.application.repository.TokenRepository;
import com.fullstack.fametechnologytask.application.service.TokenService;
import com.fullstack.fametechnologytask.application.shared.Utils;

@Service
public class TokenServiceImpl implements TokenService {
	
	@Autowired
	TokenRepository tokenRepository;
	
	@Autowired
	Utils utils;

	@Override
	public VerificationTokenEntity createVerificationToken(UserEntity savedUser) {
		
		VerificationTokenEntity token = new VerificationTokenEntity();
		
		token.setTokenId(utils.generateToken());
		token.setUserDetails(savedUser);
		
		return tokenRepository.save(token);
	}

	@Override
	public UserEntity getUserDetailsFromToken(String tokenId) {
		
		VerificationTokenEntity token = tokenRepository.findTokenByTokenId(tokenId);
		
		return token.getUserDetails();
		
	}

	@Override
	public void deleteToken(String tokenId) {

		VerificationTokenEntity token = tokenRepository.findTokenByTokenId(tokenId);
		
		tokenRepository.delete(token);
		
	}

}
