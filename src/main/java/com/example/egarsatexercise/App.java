package com.example.egarsatexercise;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

import com.example.egarsatexercise.csv.CSVHelper;
import com.example.egarsatexercise.mail.MailConfiguration;

import javax.mail.MessagingException;

@SpringBootApplication
public class App {


	@Autowired
	private MailConfiguration senderService;

	public static void main(String[] args) {
		SpringApplication.run(App.class, args);

	}

	@EventListener(ApplicationReadyEvent.class)
	public void triggerMail() throws MessagingException {
		senderService.sendSimpleEmail("mcasajuana@egarsat.es", "Numero de registros utilizados - Mutua Egarsat",
				"Numero de registros: " + CSVHelper.cont);
	}

}
