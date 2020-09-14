package com.fullstack.fametechnologytask.application.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.Data;
import lombok.ToString;

@Entity
@Table(name = "users")
@Data
public class UserEntity implements Serializable {

	private static final long serialVersionUID = 7748943487145280906L;
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Column(nullable = false)
	private String userId;
	@Column(nullable = false)
	private String firstName;
	@Column(nullable = false)
	private String lastName;
	@Column(nullable = false)
	private String password;
	@Column(nullable = false)
	private String email;
	@Column(nullable = false)
	private String regPassword;
	
	@Column(nullable = false)
	private Date dob;
	@CreationTimestamp
	private Date creationDate;
	@UpdateTimestamp
	private Date updationDate;
	
	@Column(nullable = false)
	private boolean isVerified;
	
	
	@ToString.Exclude
	@OneToOne
	@JoinColumn(name = "images_id")
	private ImageEntity imageDetails;
}
