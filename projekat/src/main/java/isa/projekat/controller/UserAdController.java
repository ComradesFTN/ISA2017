package isa.projekat.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import isa.projekat.domain.UserAd;
import isa.projekat.service.UserAdService;

@RestController
@RequestMapping(value = "/userAd")
public class UserAdController {
	
	@Autowired
	private UserAdService userAdService;
	
	@RequestMapping(value = "getUserAds", method = RequestMethod.GET )
	public ResponseEntity<List<UserAd>> getUserAds() {
		List<UserAd> userAds = userAdService.findAll();
		return new ResponseEntity<>(userAds, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET )
	public ResponseEntity<UserAd> aproveUserAd(@PathVariable Long id) {
		UserAd userAd = userAdService.findOne(id);
		userAd.setAproved(true);
		userAdService.save(userAd);
		return new ResponseEntity<>(userAd, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<UserAd> disaproveUserAd(@PathVariable Long id) {
		UserAd disaproved = userAdService.delete(id);
		return new ResponseEntity<>(disaproved, HttpStatus.OK);
	}
	
	@RequestMapping(method = RequestMethod.POST, consumes = "application/json")
	public ResponseEntity<UserAd> addUserAd(@RequestBody UserAd userAd) {
		UserAd newUserAd = userAdService.save(userAd);
		return new ResponseEntity<>(newUserAd, HttpStatus.OK);
	}

}
