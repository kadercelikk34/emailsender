package com.email.sender.emailsender.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Stream;

@Component
public class MailUtil {

	@Autowired
    private JavaMailSender mailSender;

	public void sendMail(String from, List<String> to, List<String> cc, String subject, String msg) {
		SimpleMailMessage message = new SimpleMailMessage();

		message.setFrom(from);
		to.stream().forEach(s -> message.setTo(s));

		message.setTo((String[]) to.toArray());
		message.setCc((String[]) cc.toArray());
		message.setSubject(subject);
		message.setText(msg);
		mailSender.send(message);
		
	}
}