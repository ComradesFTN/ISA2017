package isa.projekat.controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import isa.projekat.domain.Projection;
import isa.projekat.domain.User;
import isa.projekat.domain.Visit;
import isa.projekat.domain.dto.VisitDTO;
import isa.projekat.service.ProjectionService;
import isa.projekat.service.UserService;

@Controller
@RequestMapping(value = "/visit")
public class VisitController {
	
	@Autowired
	private ProjectionService projectionService;
	
	@Autowired
	private UserService userService;

	@RequestMapping(value = "/displayVisit", method = RequestMethod.POST)
	public ResponseEntity<Visit> displayVisit(VisitDTO visitDTO) {
	
		User user = userService.findOne(visitDTO.getUser());
		Projection projection = projectionService.findOne(visitDTO.getProjection());
		
		if(projection == null) {
			System.out.println("null je projekcija");
		}
		
		if(user == null) {
			System.out.println("null je user");
		}

		//Date projectionDate = projection.getDate(); //ovaj govnar ovde je null, nzm zasto
		Date currentDate = new Date();		
		
		/*if(projectionDate.before(currentDate)) {
			System.out.println("Projekcija prosle, moze!");
		}*/
		
		return new ResponseEntity<>(HttpStatus.OK);
		
	}
}
