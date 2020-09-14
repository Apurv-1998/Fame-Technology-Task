package com.fullstack.fametechnologytask.application.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.fullstack.fametechnologytask.application.entity.ImageEntity;

@Repository
public interface ImageRepository extends CrudRepository<ImageEntity, Long> {

	Optional<ImageEntity> findImageByImageId(String imageId);

}
