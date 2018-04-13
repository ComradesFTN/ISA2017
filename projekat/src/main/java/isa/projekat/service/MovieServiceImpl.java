package isa.projekat.service;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Base64;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import isa.projekat.domain.Movie;
import isa.projekat.repository.MovieRepository;

@Service
public class MovieServiceImpl implements MovieService {

	@Autowired
	private MovieRepository movieRepository;
	
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
						String pathFile = "C:\\Users\\Goran\\Desktop\\fax\\isa\\Nas projekat 2018\\projekat2\\projekat\\src\\main\\resources\\static\\imagesMovies\\film"+System.currentTimeMillis()+".jpg";
						decoder(movie.getPoster(), pathFile);
						String splitPath[] = pathFile.split("static\\\\");
						movie.setPoster(splitPath[1]);
					}
					else {
						String pathFile = "C:\\Users\\Goran\\Desktop\\fax\\isa\\Nas projekat 2018\\projekat2\\projekat\\src\\main\\resources\\static\\"+this.findOne(movie.getId()).getPoster();
						decoder(movie.getPoster(), pathFile);
						String splitPath[] = pathFile.split("static\\\\");
						movie.setPoster(splitPath[1]);
					}
				}else {
					String pathFile = "C:\\Users\\Goran\\Desktop\\fax\\isa\\Nas projekat 2018\\projekat2\\projekat\\src\\main\\resources\\static\\imagesMovies\\film"+System.currentTimeMillis()+".jpg";
					decoder(movie.getPoster(), pathFile);
					String splitPath[] = pathFile.split("static\\\\");
					movie.setPoster(splitPath[1]);
				}
			}
		}
		System.out.println(movie.getId());
		return movieRepository.save(movie);
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
	
}
