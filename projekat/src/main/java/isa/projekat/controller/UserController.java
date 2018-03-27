package isa.projekat.controller;

import java.util.Calendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;

import isa.projekat.controller.listeners.OnRegistrationCompleteEvent;
import isa.projekat.domain.User;
import isa.projekat.domain.VerificationToken;
import isa.projekat.repository.UserRepository;
import isa.projekat.service.EmailService;
import isa.projekat.service.UserService;

@RestController
@RequestMapping(value = "/users")
public class UserController {

	@Autowired
	private UserService userService;
	
	@Autowired
	private EmailService emailService;
	
	@Autowired
	private UserRepository userRepository;
	
	@RequestMapping(method = RequestMethod.POST, consumes= "application/json")
	public ResponseEntity<User> addUser(@RequestBody User user, WebRequest request) {
		User newUser = userService.save(user);
		try {
			emailService.send(user);
		}catch( Exception e ){
			//logger.info("Greska prilikom slanja emaila: " + e.getMessage());
		}
		return new ResponseEntity<>(newUser, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/registrationConfirm" ,method = RequestMethod.GET)
	public String confirmRegistration (@RequestParam("token") String token) {
		
		VerificationToken verificationToken = userService.getVerificationToken(token);
		if(verificationToken == null) {
			System.out.println("Nema tokena");
			return "redirect:/badUser.html";
		}
		
		User user = verificationToken.getUser();
		Calendar cal = Calendar.getInstance();
	    if ((verificationToken.getExpiryDate().getTime() - cal.getTime().getTime()) <= 0) {
	        System.out.println("Istekao token");
	        return "redirect:/badUser.html";
	    } 
		
	    user.setEnabled(true);
		
		return "redirect:/index.html";
		
	}
	
	/*@RequestMapping(value = "/login", method = RequestMethod.GET)
	public boolean isUserEnabled(String email) {
		User user = userRepository.findbyEmail(email);
		if (user == null) {
			return false;
        }
		if (user.isEnabled()) {
			System.out.println("User confirmed");
			return true;
		}
		return false;
	}*/
	
	
}
