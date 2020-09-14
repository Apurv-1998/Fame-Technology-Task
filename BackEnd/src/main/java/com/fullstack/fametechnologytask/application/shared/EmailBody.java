package com.fullstack.fametechnologytask.application.shared;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmailBody {
	
	
	private String subject;
	private String recipient;
	private String body;
	
	

}
