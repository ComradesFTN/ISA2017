package isa.projekat.service;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Base64;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import isa.projekat.domain.Bid;
import isa.projekat.domain.User;
import isa.projekat.domain.UserAd;
import isa.projekat.repository.UserAdRepository;
import isa.projekat.repository.UserRepository;

@Service
public class UserAdServiceImpl implements UserAdService{
	
	@Value("${paths.menjaj}")
	private String putanja;
	
	@Autowired
	public UserAdRepository userAdRepository; 
	
	@Autowired
	public UserRepository userRepository;
	
	@Override
	public List<UserAd> findAll() {
		return userAdRepository.findAll();
	}

	@Override
	public UserAd save(UserAd userAd, Boolean aprove) {
		if(aprove==false) {
			if(userAd.getImage().contains("Bez slike")==false) {
				String pathFile = putanja+"imagesAd\\slikaOglas"+System.currentTimeMillis()+".jpg";
				decoder(userAd.getImage(), pathFile);
				String splitPath[] = pathFile.split("static\\\\");
				userAd.setImage(splitPath[1]);
			}
		}
		return userAdRepository.save(userAd);
	}
	
	@Override
	public UserAd findOne(Long id) {
		return userAdRepository.findOne(id);
	}

	@Override
	public UserAd delete(Long id) {
		UserAd userAd = userAdRepository.findOne(id);
		if(userAd == null){
			throw new IllegalArgumentException("Tried to delete"
					+ "non-existant userAd");
		}
		userAdRepository.delete(userAd);
		return userAd;
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
	public User findUserById(Long id) {
		User user = userRepository.findOne(id);
		return user;
	}

}
