package com.fullstack.fametechnologytask.application.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "verification_token")
@Data
public class VerificationTokenEntity implements Serializable {
	
	private static final long serialVersionUID = -2736744368537443119L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	@Column(name = "token",nullable = false)
	private String tokenId;
	
	@OneToOne
	@JoinColumn
	private UserEntity userDetails;

}
