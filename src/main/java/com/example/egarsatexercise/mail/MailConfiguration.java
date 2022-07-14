
package com.example.egarsatexercise.mail;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.example.egarsatexercise.csv.CSVHelper;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;

@Service
public class MailConfiguration {
	public static String valor;
	@Autowired
	private JavaMailSender mailSender;

	public void sendSimpleEmail(String toEmail, String subject, String cont) {
		while (true) {
			if (CSVHelper.cont >= 1) {
				SimpleMailMessage message = new SimpleMailMessage();
				message.setFrom("rmartbernabe@gmail.com");
				message.setTo(toEmail);
				message.setText(cont);
				message.setSubject(subject);
				mailSender.send(message);
				System.out.println("Mail Send...");
				System.out.println("number of records: " + CSVHelper.cont);
				break;

			}
		}
	}

}
