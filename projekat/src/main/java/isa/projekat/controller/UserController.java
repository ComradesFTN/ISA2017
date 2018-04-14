package isa.projekat.controller;

import java.util.List;

import javax.json.Json;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;

import isa.projekat.domain.User;
import isa.projekat.domain.VerificationToken;
import isa.projekat.domain.dto.SearchNameDTO;
import isa.projekat.domain.dto.UserDTO;
import isa.projekat.repository.UserRepository;
import isa.projekat.service.EmailService;
import isa.projekat.service.UserService;

@Controller
//@RequestMapping(value = "/users")
public class UserController {
	
	@Autowired
	private UserService userService;

	@Autowired
	private EmailService emailService;

	@RequestMapping(method = RequestMethod.POST, consumes = "application/json", value = "/users")
	public ResponseEntity<User> addUser(@RequestBody User user) {
		User newUser = userService.save(user); // TODO proveri da li ima email isti pre toga

		try {
			emailService.send(user);
		} catch (Exception e) {
			System.out.println("Greska prilikom slanja emaila: " + e.getMessage());
		}
		return new ResponseEntity<>(newUser, HttpStatus.OK);
	}

	@RequestMapping(value = "/users/registrationConfirm", method = RequestMethod.GET)
	public RedirectView confirmRegistration(@RequestParam("token") String token) {

		VerificationToken verificationToken = userService.getVerificationToken(token);
		if (verificationToken == null) {
			System.out.println("Nema tokena");
			return new RedirectView("NeregistrovaniKorisnici/badUser.html");
		}

		User user = verificationToken.getUser();

		user.setEnabled(true);
		userService.save(user);
		return new RedirectView("http://localhost:8080/NeregistrovaniKorisnici/login.html");

	}

	@RequestMapping(value = "users/login", method = RequestMethod.POST, consumes = "application/json")
	public ResponseEntity<UserDTO> loginProcess(@RequestBody UserDTO userDTO, HttpSession session) {
		String email = userDTO.getEmail();
		String pass = userDTO.getPassword();
		User currentUser = userService.getUserByEmail(email);
		if(currentUser == null) {
			System.out.println("Nepostojeci mail");
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			
		}
		if(currentUser.getPassword().equals(pass)) {
			if(currentUser.isEnabled()) {
				session.setAttribute("loggedUser", currentUser);
				return new ResponseEntity<>(HttpStatus.OK);
			} else return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
			
		}
		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

		
	}
	
	@RequestMapping(method = RequestMethod.POST, consumes = "application/json", value = "/userEdit")
	public ResponseEntity<User> updateUser(@RequestBody User user, HttpSession session) {
		userService.updateUser(user);
		session.setAttribute("loggedUser", user);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/getUsers")
	public ResponseEntity<List<User>> getUsers() {
		List<User> users = userService.findAll();
		return new ResponseEntity<>(users, HttpStatus.OK);
	}
	
}
