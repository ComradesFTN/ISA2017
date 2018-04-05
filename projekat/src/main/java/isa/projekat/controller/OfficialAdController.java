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

import isa.projekat.domain.OfficialAd;
import isa.projekat.service.OfficialAdService;

@RestController
@RequestMapping(value = "/officialAd")
public class OfficialAdController {
	
	@Autowired
	private OfficialAdService officialAdService;

	@RequestMapping(value = "getOfficialAds", method = RequestMethod.GET )
	public ResponseEntity<List<OfficialAd>> getOfficialAds() {
		List<OfficialAd> officialAds = officialAdService.findAll();
		return new ResponseEntity<>(officialAds, HttpStatus.OK);
	}
	
	@RequestMapping(method = RequestMethod.POST, consumes = "application/json")
	public ResponseEntity<OfficialAd> addOfficialAd(@RequestBody OfficialAd officialAd) {
		OfficialAd newOfficialAd = officialAdService.save(officialAd);
		return new ResponseEntity<>(newOfficialAd, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET )
	public ResponseEntity<OfficialAd> getOfficialAd(@PathVariable Long id) {
		OfficialAd officialAd = officialAdService.findOne(id);
		return new ResponseEntity<>(officialAd, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<OfficialAd> deleteOfficialAd(@PathVariable Long id) {
		OfficialAd deleted = officialAdService.delete(id);
		return new ResponseEntity<>(deleted, HttpStatus.OK);
	}

}
