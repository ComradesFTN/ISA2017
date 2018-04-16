package isa.projekat.service;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Base64;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import isa.projekat.domain.Auditorium;
import isa.projekat.domain.Cinema;
import isa.projekat.domain.Movie;
import isa.projekat.repository.AuditoriumRepository;
import isa.projekat.repository.CinemaRepository;
import isa.projekat.repository.MovieRepository;

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
	
	@Override
	public List<Movie> findAll() {
		return movieRepository.findAll();
	}

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
		Cinema cinema = cinemaRepository.findOne(movie.getCinemaId());
		for(Auditorium auditorium : cinema.getAuditoriums()){
			auditorium.getMovies().remove(savedMovie);	
			auditoriumRepository.save(auditorium);
		}
		for(Auditorium auditorium : movie.getAuditoriums()){
			System.out.println(auditorium.getName());
			auditorium.getMovies().add(savedMovie);
			auditoriumRepository.save(auditorium);
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
		movieRepository.delete(movie);
		return movie;
	}
	
}
