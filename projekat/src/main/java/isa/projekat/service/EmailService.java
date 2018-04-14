package isa.projekat.service;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import isa.projekat.domain.User;
import isa.projekat.domain.VerificationToken;

@Service
public class EmailService {

	@Autowired
	private JavaMailSender javaMailSender;
	@Autowired
	private Environment env;
	@Autowired
	private UserService service;
	
	@Async
	public void send(User user) throws MailException, InterruptedException {
		
		String token = UUID.randomUUID().toString();
		String confirmationUrl = "/users/registrationConfirm.html?token=" + token;
		service.createVerificationToken(user, token);

		SimpleMailMessage mail = new SimpleMailMessage();
		mail.setTo(user.getEmail());
		mail.setFrom(env.getProperty("spring.mail.username"));
		mail.setSubject("Potvrdite email ISA");
		mail.setText("Pozdrav " + user.getFirstName() + ",\n\n Aktivacioni link je " + "http://localhost:8080" + confirmationUrl);
		javaMailSender.send(mail);

		System.out.println("Email poslat!");
	}
	
	@Async
	public void sendBidSold(User user, String userAdName) throws MailException, InterruptedException {

		SimpleMailMessage mail = new SimpleMailMessage();
		mail.setTo(user.getEmail());
		mail.setFrom(env.getProperty("spring.mail.username"));
		mail.setSubject("Obavestenje o ponudi");
		mail.setText("Postovani " + user.getFirstName() + ",\n\n Ponuda koju ste dali na oglas pod nazivom " + userAdName + " je prihvacena.");
		javaMailSender.send(mail);

		System.out.println("Email poslat!");
	}
	
	@Async
	public void sendBidRejected(User user, String userAdName) throws MailException, InterruptedException {
		
		SimpleMailMessage mail = new SimpleMailMessage();
		mail.setTo(user.getEmail());
		mail.setFrom(env.getProperty("spring.mail.username"));
		mail.setSubject("Obavestenje o ponudi");
		mail.setText("Postovani " + user.getFirstName() + ",\n\n Ponuda koju ste dali na oglas pod nazivom " + userAdName + " nije prihvacena.");
		javaMailSender.send(mail);

		System.out.println("Email poslat!");
	}


}
