package com.verification.emailVerification.ServicesImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.verification.emailVerification.Services.EmailServices;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

@Service
public class EmailServiceImpl implements EmailServices {
	@Autowired
	private JavaMailSender javaMailSender;

	@Override
	public void sendEmail(String to, String subject, String body) {

		MimeMessage message = javaMailSender.createMimeMessage();

		try {

			MimeMessageHelper helper = new MimeMessageHelper(message, true);

			helper.setTo(to);
			helper.setSubject(subject);
			helper.setText(body);
			
			javaMailSender.send(message);

		} catch (MessagingException e) {

			e.getMessage();
			e.printStackTrace();
		}
	}

}
