package com.fullstack.fametechnologytask.application.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.fullstack.fametechnologytask.application.entity.VerificationTokenEntity;

@Repository
public interface TokenRepository extends CrudRepository<VerificationTokenEntity, Long> {

	
	@Query(value = "SELECT * FROM verification_token v WHERE v.token = ?1",nativeQuery = true)
	VerificationTokenEntity findTokenByTokenId(String tokenId);

}
