package isa.projekat.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import isa.projekat.domain.Auditorium;
import isa.projekat.domain.Cinema;
import isa.projekat.domain.Movie;
import isa.projekat.domain.Projection;
import isa.projekat.repository.AuditoriumRepository;
import isa.projekat.repository.CinemaRepository;
import isa.projekat.repository.MovieRepository;
import isa.projekat.repository.ProjectionRepository;

@Service
public class CinemaServiceImpl implements CinemaService {

	@Autowired
	private CinemaRepository cinemaRepository;
	
	@Autowired
	private MovieRepository movieRepository;
	
	@Autowired 
	private AuditoriumRepository auditoriumRepository;
	
	@Autowired
	private ProjectionRepository projectionRepository;
	
	@Temporal(TemporalType.DATE)
	private Date date = new Date();

	@Override
	public List<Cinema> findAll() {
		return cinemaRepository.findAll();
	}

	@Override
	public Cinema save(Cinema cinema) {
		return cinemaRepository.save(cinema);
	}

	@Override
	public Cinema findOne(Long id) {
		return cinemaRepository.findOne(id);
	}

	@Override
	public void refresh(Cinema cinema) {		
		Set<Movie> movies = cinema.getMovies();
		for(Movie movie : movies){
			for(String term : movie.getTerm()){			
				for(Auditorium audit : movie.getAuditoriums()){
					for(int i=1; i<=10;i++){
						Projection proj = projectionRepository.findByMovie_idAndDateAndAuditoriumIdAndTerm(movie.getId(), new Date(date.getTime() + TimeUnit.DAYS.toMillis( i )), audit.getId(), term);
						if(proj==null){
							proj = new Projection();
							proj.setAuditoriumId(audit.getId());
							proj.setDate(new Date(date.getTime() + TimeUnit.DAYS.toMillis( i )));
							proj.setMovie(movie);
							proj.setTerm(term);
							List<Integer> seats = new ArrayList<Integer>();
							for(Integer seat : auditoriumRepository.findOne(audit.getId()).getSeats()){
								seats.add(seat);
							}
							proj.setSeats(seats);
							projectionRepository.save(proj);
						}
					}
				}
			}
		}
	}	

}
