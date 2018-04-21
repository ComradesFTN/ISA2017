package isa.projekat.service;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import isa.projekat.domain.Cinema;
import isa.projekat.domain.Reservation;
import isa.projekat.domain.Theater;
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
	
	@Async
	public void sendReservationInvite(User user, Reservation reservation, User inviter, Cinema cinema, String auditoriumName) throws MailException, InterruptedException { 
		
		SimpleMailMessage mail = new SimpleMailMessage();
		mail.setTo(user.getEmail());
		mail.setFrom(env.getProperty("spring.mail.username"));
		mail.setSubject("Obavestenje o pozivu za rezervisane karte");
		mail.setText("Postovani " + user.getFirstName() + ",\n\n Pozvao vas je "+inviter.getFirstName()+" da idete zajedno na projekciju "+reservation.getProjection().getMovie().getName()+"\n\n Datum : "+reservation.getProjection().getDate()+" Termin : "+reservation.getProjection().getTerm()+" Bioskop : "+cinema.getName()+" Sala: "+auditoriumName+" "
				+ "\n\n Molimo Vas potvrdite rezervaciju klikom na sledeci link - http://localhost:8080/reservations/reservationConfirmed/"+user.getId()+"/"+inviter.getId()+""
						+ "\n\n\n\n Ukoliko zelite mozete odbiti rezervaciju klikom na sledeci link - "+"http://localhost:8080/reservations/reservationRejected/"+user.getId()+"/"+inviter.getId()+"/"+reservation.getId());	
		javaMailSender.send(mail);
		
		System.out.println("Email poslat!");
		System.out.println(mail.getText());
	}
	
	@Async
	public void sendReservationInvite(User user, Reservation reservation, User inviter, Theater theater, String auditoriumName) throws MailException, InterruptedException { 
		
		SimpleMailMessage mail = new SimpleMailMessage();
		mail.setTo(user.getEmail());
		mail.setFrom(env.getProperty("spring.mail.username"));
		mail.setSubject("Obavestenje o pozivu za rezervisane karte");
		mail.setText("Postovani " + user.getFirstName() + ",\n\n Pozvao vas je "+inviter.getFirstName()+" da idete zajedno na projekciju "+reservation.getProjection().getMovie().getName()+"\n\n Datum : "+reservation.getProjection().getDate()+" Termin : "+reservation.getProjection().getTerm()+" Pozoriste : "+theater.getName()+" Sala: "+auditoriumName+" "
				+ "\n\n Molimo Vas potvrdite rezervaciju klikom na sledeci link - http://localhost:8080/reservations/reservationConfirmed/"+user.getId()+"/"+inviter.getId()+""
						+ "\n\n\n\n Ukoliko zelite mozete odbiti rezervaciju klikom na sledeci link - "+"http://localhost:8080/reservations/reservationRejected/"+user.getId()+"/"+inviter.getId()+"/"+reservation.getId());	
		javaMailSender.send(mail);
		
		System.out.println("Email poslat!");
		System.out.println(mail.getText());
	}

	@Async
	public void sendReservationInviteConfirmed(User user, User inviter){
		SimpleMailMessage mail = new SimpleMailMessage();
		mail.setTo(user.getEmail());
		mail.setFrom(env.getProperty("spring.mail.username"));
		mail.setSubject("Obavestenje o pozivu za rezervisane karte");
		mail.setText("Postovani " + inviter.getFirstName() + ",\n\n Vas prijatelj " + user.getFirstName() + " je prihvatio vas poziv za odlazak na projekciju");
		javaMailSender.send(mail);
		
		System.out.println("Email poslat!");
		System.out.println(mail.getText());
	}
	
	@Async
	public void sendReservationInviteRejected(User user, User inviter){
		SimpleMailMessage mail = new SimpleMailMessage();
		mail.setTo(user.getEmail());
		mail.setFrom(env.getProperty("spring.mail.username"));
		mail.setSubject("Obavestenje o pozivu za rezervisane karte");
		mail.setText("Postovani " + inviter.getFirstName() + ",\n\n Vas prijatelj " + user.getFirstName() + " je odbio vas poziv za odlazak na projekciju. Rezervacija njegovog mesta je obrisana");
		javaMailSender.send(mail);
		
		System.out.println("Email poslat!");
		System.out.println(mail.getText());
	}
}
