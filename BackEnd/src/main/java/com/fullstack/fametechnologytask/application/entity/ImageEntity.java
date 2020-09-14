package com.fullstack.fametechnologytask.application.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "images")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ImageEntity implements Serializable {

	private static final long serialVersionUID = -3884855132378635907L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Column(nullable = false)
	private String imageId;
	@Column(nullable = false)
	private String imageName;
	@Column(nullable = false)
	private String type;
	@Column(nullable = false)
	@Lob
	private byte[] picByte;

}
