package isa.projekat.service;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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
public class MovieServiceImpl implements MovieService {

	@Value("${paths.menjaj}")
	private String putanja;
	
	@Autowired
	private CinemaRepository cinemaRepository;
	
	@Autowired
	private MovieRepository movieRepository;
	
	@Autowired 
	private AuditoriumRepository auditoriumRepository;
	
	@Autowired
	private ProjectionRepository projectionRepository;
	
	@Override
	public List<Movie> findAll() {
		return movieRepository.findAll();
	}
	
	@Temporal(TemporalType.DATE)
	private Date date = new Date();

	@Override
	public Movie save(Movie movie) {
		
		if(!movie.getPoster().contains("Bez")) {			
			if(!movie.getPoster().contains("imagesAd")) {
				if(this.findOne(movie.getId())!=null) {
					if(this.findOne(movie.getId()).getPoster().contains("Bez")) {
						String pathFile = putanja+"imagesMovies\\film"+System.currentTimeMillis()+".jpg";
						decoder(movie.getPoster(), pathFile);
						String splitPath[] = pathFile.split("static\\\\");
						movie.setPoster(splitPath[1]);
					}
					else {
						String pathFile = putanja+this.findOne(movie.getId()).getPoster();
						decoder(movie.getPoster(), pathFile);
						String splitPath[] = pathFile.split("static\\\\");
						movie.setPoster(splitPath[1]);
					}
				}else {
					String pathFile = putanja+"imagesMovies\\film"+System.currentTimeMillis()+".jpg";
					decoder(movie.getPoster(), pathFile);
					String splitPath[] = pathFile.split("static\\\\");
					movie.setPoster(splitPath[1]);
				}
			}
		}
		Movie savedMovie=movieRepository.save(movie);
		
		List<Projection> projections = projectionRepository.findAll();
		for(Projection proj : projections) {
			if(proj.getMovie().getId()==savedMovie.getId()){
				projectionRepository.delete(proj);
			}
		}
		
		for(String term : savedMovie.getTerm()){			
			for(Auditorium audit : savedMovie.getAuditoriums()){
				for(int i=1; i<=10;i++){
					Projection proj = new Projection();
					proj.setAuditoriumId(audit.getId());
					proj.setDate(new Date(date.getTime() + TimeUnit.DAYS.toMillis( i )));
					proj.setMovie(savedMovie);
					proj.setTerm(term);
					List<Integer> seats = new ArrayList<Integer>();
					for(Integer seat : auditoriumRepository.findOne(audit.getId()).getSeats()){
						seats.add(seat);
					}
					Projection saveProj=projectionRepository.save(proj);
					saveProj.setSeats(seats);
					projectionRepository.save(saveProj);
				}
			}
		}	
		return savedMovie;
	}

	@Override
	public Movie findOne(Long id) {
		return movieRepository.findOne(id);
	}
	
	
	
	public static void decoder(String base64Image, String pathFile) {
		try (FileOutputStream imageOutFile = new FileOutputStream(pathFile)) {
			// Converting a Base64 String into Image byte array
			byte[] imageByteArray = Base64.getDecoder().decode(base64Image);
			imageOutFile.write(imageByteArray);
		} catch (FileNotFoundException e) {
			System.out.println("Image not found" + e);
		} catch (IOException ioe) {
			System.out.println("Exception while reading the Image " + ioe);
		}
	}

	@Override
	public Movie delete(Long id) {
		Movie movie = movieRepository.findOne(id);
		if(movie == null){
			throw new IllegalArgumentException("Tried to delete"
					+ "non-existant movie");
		}
		projectionRepository.delete(projectionRepository.findByMovie_id(id));
		movieRepository.delete(movie);
		return movie;
	}
	
}
