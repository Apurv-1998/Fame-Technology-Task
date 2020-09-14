package com.fullstack.fametechnologytask.application.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.fullstack.fametechnologytask.application.entity.UserEntity;

@Repository
public interface UserRepository extends CrudRepository<UserEntity, Long> {

	UserEntity findUserByUserId(String userId);

	List<UserEntity> findUserByEmail(String email);

}
