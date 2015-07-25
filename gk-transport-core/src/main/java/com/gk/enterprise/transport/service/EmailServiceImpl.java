package com.gk.enterprise.transport.service;

import java.util.Properties;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;

import com.gk.enterprise.transport.bean.EmailBean;

public class EmailServiceImpl {

	private static final String username = "support@laneaxis.com";
	private static final String password = "L@n3@x!s@1234";

	public void sendEmail(EmailBean emailBean) throws MessagingException {

		String host = "smtp.gmail.com";
		String port = "25";
		JavaMailSenderImpl javaMailSender = new JavaMailSenderImpl();
		javaMailSender.setHost(host);
		javaMailSender.setPort(Integer.parseInt(port));
		javaMailSender.setUsername(username);
		javaMailSender.setPassword(password);
		javaMailSender.setJavaMailProperties(getMailProperties());

		MimeMessage mimeMessage = javaMailSender.createMimeMessage();
		MimeMessageHelper message = new MimeMessageHelper(mimeMessage);

		message.setFrom(username);
		message.setTo(emailBean.getEmailIds());
		message.setSubject("Document No");
		message.setText(emailBean.getEmailContent(), true);

		javaMailSender.send(mimeMessage);
	}

	private Properties getMailProperties() {
		String smtpAuth = "true";
		String starttls = "true";
		String timeout = "200000";
		Properties properties = new Properties();
		properties.setProperty("mail.smtp.auth", smtpAuth);
		properties.setProperty("mail.smtp.starttls.enable", starttls);
		properties.put("mail.smtp.timeout", timeout);
		return properties;
	}

}
