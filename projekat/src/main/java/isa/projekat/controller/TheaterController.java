package isa.projekat.controller;

import java.net.URI;
import java.util.HashSet;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import isa.projekat.domain.Auditorium;
import isa.projekat.domain.Cinema;
import isa.projekat.domain.Movie;
import isa.projekat.domain.Show;
import isa.projekat.domain.Theater;
import isa.projekat.service.ShowService;
import isa.projekat.service.TheaterService;

@RestController
@RequestMapping(value = "/theatres")
public class TheaterController {
	
	@Autowired
	private TheaterService theaterService;
	
	@Autowired
	private ShowService showService;

	@RequestMapping(value = "getTheatres", method = RequestMethod.GET )
	public ResponseEntity<List<Theater>> getTheatres() {
		List<Theater> theatres = theaterService.findAll();
		return new ResponseEntity<>(theatres, HttpStatus.OK);
	}
	
	@RequestMapping(value = "{id}", method = RequestMethod.GET)
	public ResponseEntity<Theater> getTheatre(@PathVariable Long id) {
		Theater theater = theaterService.findOne(id);
		return new ResponseEntity<>(theater, HttpStatus.OK);
	}
	
	@RequestMapping(method = RequestMethod.POST, consumes = "application/json" )
	public ResponseEntity<Theater> addTheatre(@RequestBody Theater theater) {
		for(Auditorium auditorium : theater.getAuditoriums()){
			for(int i=0;i<auditorium.getRows()*auditorium.getColumns();i++){
				auditorium.getSeats().add(Integer.valueOf(1));
			}
		}		
		Theater newTheater = theaterService.save(theater);
		return new ResponseEntity<>(newTheater, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/{id}",method = RequestMethod.PUT)
	public ResponseEntity<Theater> editTheater(@PathVariable Long id,@RequestBody Theater theater) {
		Theater editTheater=theaterService.findOne(id);
		editTheater.setName(theater.getName());
		editTheater.setAdress(theater.getAdress());
		editTheater.setDescription(theater.getDescription());
		Theater editedTheater = theaterService.save(editTheater);
		return new ResponseEntity<>(editedTheater, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/{id}/getShows", method = RequestMethod.GET )
	public ResponseEntity<HashSet<Show>> getTheaterShows(@PathVariable Long id) {
		HashSet<Show> shows = new HashSet<Show>(theaterService.findOne(id).getShows());
		return new ResponseEntity<>(shows, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/{theater_id}/addShow/{show_id}", method = RequestMethod.PUT )
	public ResponseEntity<Show> addShow(@PathVariable Long theater_id,@PathVariable Long show_id) {
		Theater theater = theaterService.findOne(theater_id);
		Show show = showService.findOne(show_id);
		theater.getShows().add(show);
		theaterService.save(theater);
		return new ResponseEntity<>(show, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/{theater_id}/editMovie/{show_id}", method = RequestMethod.PUT )
	public ResponseEntity<Show> editShow(@PathVariable Long theater_id,@PathVariable Long show_id) {
		Theater theater = theaterService.findOne(theater_id);
		Show show = showService.findOne(show_id);
		for(Show removeShow:theater.getShows()){
			if(show.getId()==removeShow.getId()){
				theater.getShows().remove(removeShow);
				theater.getShows().add(show);
				break;
			}
		}
		theaterService.save(theater);
		return new ResponseEntity<>(show, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/refreshTheater/{id}", method = RequestMethod.GET)
	public ResponseEntity<Theater> refreshTheater(@PathVariable Long id) {
		Theater theater = theaterService.findOne(id);
		theaterService.refresh(theater);
		return new ResponseEntity<>(theater, HttpStatus.OK);
	}
}
