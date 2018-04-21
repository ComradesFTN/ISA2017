package isa.projekat.controller;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import isa.projekat.domain.Show;
import isa.projekat.domain.Theater;
import isa.projekat.service.ShowService;
import isa.projekat.service.TheaterService;

@RestController
@RequestMapping(value = "/shows")
public class ShowController {

	@Autowired
	private ShowService showService;
	
	@Autowired
	private TheaterService theaterService;

	@RequestMapping(value = "getShows", method = RequestMethod.GET )
	public ResponseEntity<List<Show>> getShows() {
		List<Show> shows = showService.findAll();
		return new ResponseEntity<>(shows, HttpStatus.OK);
	}
	
	@RequestMapping(value = "{id}", method = RequestMethod.GET)
	public ResponseEntity<Show> getShow(@PathVariable Long id) {
		Show show = showService.findOne(id);
		return new ResponseEntity<>(show, HttpStatus.OK);
	}
	
	@RequestMapping(method = RequestMethod.POST, consumes = "application/json")
	public ResponseEntity<Show> addShow(@RequestBody Show show) {	
		Show newShow = showService.save(show);
		return new ResponseEntity<>(newShow, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/{id}",method = RequestMethod.PUT ,consumes = "application/json")
	public ResponseEntity<Show> editShow(@PathVariable Long id,@RequestBody Show show) {		
		show.setId(id);		
		Show editedShow = showService.save(show);
		return new ResponseEntity<>(editedShow, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Show> deleteShow(@PathVariable Long id) {
		List<Theater> theaters=theaterService.findAll();	
		Show deletedShow = showService.findOne(id);
		for(Theater theater : theaters){
			Set<Show> theaterShows = theater.getShows();
			if(theaterShows.remove(deletedShow)){
				theaterService.save(theater);			
			}
		}
		deletedShow = showService.delete(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
}
