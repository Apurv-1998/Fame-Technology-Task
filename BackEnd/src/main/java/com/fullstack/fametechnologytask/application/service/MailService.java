package com.fullstack.fametechnologytask.application.service;

import com.fullstack.fametechnologytask.application.shared.EmailBody;

public interface MailService {

	void sendMail(EmailBody emailBody);

	String buildMessage(String message);

}
