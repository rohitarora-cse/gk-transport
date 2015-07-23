package com.gk.enterprise.transport.service;

import java.util.Properties;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import com.gk.enterprise.transport.bean.EmailBean;

public class EmailServiceImpl {

	public void sendEmail(EmailBean emailBean) {

		JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
		
		Properties javaMailProperties = new Properties();
		
		javaMailProperties.put("mail.smtp.host", "smtp.gmail.com");
		javaMailProperties.put("mail.smtp.socketFactory.port", "465");
		javaMailProperties.put("mail.smtp.socketFactory.class",
				"javax.net.ssl.SSLSocketFactory");
		javaMailProperties.put("mail.smtp.auth", "true");
		javaMailProperties.put("mail.smtp.port", "465");
		/*
		javaMailProperties.put("mail.smtp.auth", "true");
		javaMailProperties.put("mail.smtp.starttls.enable", "true");
		javaMailProperties.put("mail.smtp.host", "smtp.gmail.com");
		javaMailProperties.put("mail.smtp.port", "587");*/
		
		mailSender.setJavaMailProperties(javaMailProperties);
		mailSender.setUsername("rohitarora.cse@gmail.com");
		mailSender.setPassword("itsdifferent");

		SimpleMailMessage message = new SimpleMailMessage();
		 
		message.setFrom("rohitarora.cse@gmail.com");
		message.setTo(emailBean.getEmailIds());
		message.setSubject("Document No");
		message.setText(emailBean.getEmailContent());
		mailSender.send(message);	
		
		System.out.println("success");
	}
}
