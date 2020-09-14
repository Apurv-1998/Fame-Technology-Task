package com.fullstack.fametechnologytask.application.shared;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.security.SecureRandom;
import java.util.Random;
import java.util.UUID;
import java.util.zip.DataFormatException;
import java.util.zip.Deflater;
import java.util.zip.Inflater;

import org.springframework.stereotype.Service;

import com.fullstack.fametechnologytask.application.entity.UserEntity;
import com.github.marlonlom.utilities.timeago.TimeAgo;

@Service
public class Utils {

	private Random RANDOM = new SecureRandom();
	private final String USER_SALT = "DSOIFHJIOEHROIFGPWQidoshfoierqhfoiwheroigfhweoirhgwoierhgiuwrehgiuwhguhy984y3986758934687926398465";

	public String generateUserId(int length) {
		StringBuilder sb = new StringBuilder(length);

		for (int i = 0; i < length; i++) {
			sb.append(USER_SALT.charAt(RANDOM.nextInt(USER_SALT.length())));
		}

		return new String(sb);
	}

	public String generateToken() {

		String verificationToken = UUID.randomUUID().toString();

		return verificationToken;
	}

	public byte[] compressImage(byte[] data) {
		
		System.out.println("Initial Size -> "+data.length);
		
		Deflater deflater = new Deflater();
		deflater.setInput(data);
		deflater.finish();
		
		ByteArrayOutputStream output = new ByteArrayOutputStream(data.length);
		
		byte[] buffer = new byte[1024];
		
		while(!deflater.finished()) {
			
			int count = deflater.deflate(buffer);
			
			output.write(buffer, 0, count);
		}
		try {
			
			output.close();
			
		}catch(Exception e) {
			
		}
		
		System.out.println("Compressed Image Byte Size -> "+output.toByteArray().length);
		
		return output.toByteArray();
		
		
	}

	public byte[] decompressImage(byte[] data) {
		
		Inflater inflater = new Inflater();
		inflater.setInput(data);
		
		ByteArrayOutputStream output = new ByteArrayOutputStream(data.length);
		
		byte[] buffer = new byte[1024];
		
		try {
			
			while(!inflater.finished()) {
				
				int count = inflater.inflate(buffer);
				
				output.write(buffer, 0, count);
			}
			
			output.close();
			
		}catch(IOException e) {
			
		}catch(DataFormatException d) {
			
		}
		
		return output.toByteArray();
	}

	public String getDuration(UserEntity user) {
		
		return TimeAgo.using(user.getCreationDate().toInstant().toEpochMilli());
		
	}

	public String getUpdatedTime(UserEntity user) {
		
		return TimeAgo.using(user.getUpdationDate().toInstant().toEpochMilli());
		
	}

}
