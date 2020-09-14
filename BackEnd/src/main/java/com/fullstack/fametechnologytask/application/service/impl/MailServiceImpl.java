package com.fullstack.fametechnologytask.application.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import com.fullstack.fametechnologytask.application.service.MailService;
import com.fullstack.fametechnologytask.application.shared.EmailBody;

@Service
public class MailServiceImpl implements MailService {
	
	@Autowired
	TemplateEngine template;
	
	@Autowired
	JavaMailSender mailSender;

	@Override
	public void sendMail(EmailBody emailBody) {
		
		MimeMessagePreparator messagePreparator = mimeMessage -> {
			
			MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage);
			
			messageHelper.setFrom("fullstack@gmail.com");
			messageHelper.setTo(emailBody.getRecipient());
			messageHelper.setSubject(emailBody.getSubject());
			messageHelper.setText(buildMessage(emailBody.getBody()));
			
		};
		
		try {
			
			mailSender.send(messagePreparator);
			System.out.println("Mail Send !!!!");
			
			
		}catch(Exception e) {
			
			e.printStackTrace();
		}
		
		
	}
	
	@Override
	public String buildMessage(String message) {
		
		Context context = new Context();
		context.setVariable("message", message);
		
		return template.process("emailTemplate", context);
	}


}
