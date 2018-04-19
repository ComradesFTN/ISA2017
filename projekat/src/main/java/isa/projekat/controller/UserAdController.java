package isa.projekat.controller;

import java.awt.image.BufferedImage;
import java.io.FileOutputStream;
import java.util.Base64;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.xml.bind.DatatypeConverter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.MailException;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import isa.projekat.domain.Bid;
import isa.projekat.domain.User;
import isa.projekat.domain.UserAd;
import isa.projekat.service.EmailService;
import isa.projekat.service.UserAdService;
import isa.projekat.service.UserService;

@RestController
@RequestMapping(value = "/userAd")
public class UserAdController {
	
	@Autowired
	private UserAdService userAdService;
	
	@Autowired
	private EmailService emailService;
	
	@RequestMapping(value = "getUserAds", method = RequestMethod.GET )
	public ResponseEntity<List<UserAd>> getUserAds() {
		List<UserAd> userAds = userAdService.findAll();
		return new ResponseEntity<>(userAds, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET )
	public ResponseEntity<UserAd> aproveUserAd(@PathVariable Long id) {
		UserAd userAd = userAdService.findOne(id);
		userAd.setAproved(true);
		userAdService.save(userAd,true);
		return new ResponseEntity<>(userAd, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/getUserAd/{id}", method = RequestMethod.GET )
	public ResponseEntity<UserAd> getUserAd(@PathVariable Long id) {
		UserAd userAd = userAdService.findOne(id);
		return new ResponseEntity<>(userAd, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/getBids/{id}", method = RequestMethod.GET )
	public ResponseEntity<Set<Bid>> getBids(@PathVariable Long id) {
		UserAd userAd = userAdService.findOne(id);
		Set<Bid> bids= userAd.getBids();	
		return new ResponseEntity<>(bids, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<UserAd> disaproveUserAd(@PathVariable Long id) {
		UserAd disaproved = userAdService.delete(id);
		return new ResponseEntity<>(disaproved, HttpStatus.OK);
	}
	
	@RequestMapping(method = RequestMethod.POST, consumes = "application/json")
	public ResponseEntity<UserAd> addUserAd(@RequestBody UserAd userAd) {
		UserAd newUserAd = userAdService.save(userAd, false);
		return new ResponseEntity<>(newUserAd, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/makeBid/{id}",method = RequestMethod.POST, consumes = "application/json")
	public ResponseEntity<UserAd> makeBid(@RequestBody Bid bid,@PathVariable Long id) {
		UserAd userAd = userAdService.findOne(id);
		userAd.addBid(bid);
		UserAd newUserAd = userAdService.save(userAd, true);
		return new ResponseEntity<>(newUserAd, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/editBid/{userAdId}/{bidderId}",method = RequestMethod.PUT)
	public ResponseEntity<UserAd> editPriceBid(@PathVariable Long userAdId,@PathVariable Long bidderId,@RequestBody  Bid bid) {
		UserAd userAd = userAdService.findOne(userAdId);
		for(Bid b:userAd.getBids()) {
			if(b.getUserId() == bidderId) {
				b.setprice(bid.getprice());
			}
		}
		UserAd editedUserAd = userAdService.save(userAd, true);
		return new ResponseEntity<>(editedUserAd, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/chooseBid/{userAdId}/{bidderId}", method = RequestMethod.DELETE)
	public ResponseEntity<UserAd> chooseBid(@PathVariable Long userAdId, @PathVariable Long bidderId) {
		UserAd userAd = userAdService.findOne(userAdId);
		User user = userAdService.findUserById(bidderId);
		try {
			emailService.sendBidSold(user, userAd.getName());
		} catch (MailException | InterruptedException e) {
			System.out.println("Greska prilikom slanja emaila: " + e.getMessage());
		}
		for(Bid b:userAd.getBids()) {
			User user2 = userAdService.findUserById(b.getUserId());
			if(b.getUserId() != bidderId) {
				try {
					emailService.sendBidRejected(user2, userAd.getName());
				} catch (MailException | InterruptedException e) {
					System.out.println("Greska prilikom slanja emaila: " + e.getMessage());
				}
			}
		}
		UserAd deleted = userAdService.delete(userAdId);
		return new ResponseEntity<>(deleted, HttpStatus.OK);
	}

}
