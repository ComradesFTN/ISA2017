package isa.projekat.service;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Base64;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import isa.projekat.domain.OfficialAd;
import isa.projekat.repository.OfficialAdRepository;

@Service
public class OfficialAdServiceImpl implements OfficialAdService{

	@Autowired
	private OfficialAdRepository officialAdRepository;
	
	@Override
	public List<OfficialAd> findAll() {
		return officialAdRepository.findAll();
	}

	@Override
	public OfficialAd save(OfficialAd officialAd) {
		if(officialAd.getImage()!="Bez slike") {
			if(!officialAd.getImage().contains("AdminFanZone")) {
				if(this.findOne(officialAd.getId())!=null) {
					if(this.findOne(officialAd.getId()).getImage().contains("Bez")) {
						String pathFile = "C:\\Users\\HP\\git\\ISA2017\\projekat\\src\\main\\resources\\static\\AdminFanZone\\Slike\\zvanicniOglas"+System.currentTimeMillis()+".jpg";
						decoder(officialAd.getImage(), pathFile);
						String splitPath[] = pathFile.split("static\\\\");
						officialAd.setImage(splitPath[1]);
					}
					else {
						String pathFile = "C:\\Users\\HP\\git\\ISA2017\\projekat\\src\\main\\resources\\static\\"+this.findOne(officialAd.getId()).getImage();
						decoder(officialAd.getImage(), pathFile);
						String splitPath[] = pathFile.split("static\\\\");
						officialAd.setImage(splitPath[1]);
					}
				}else {
					String pathFile = "C:\\Users\\HP\\git\\ISA2017\\projekat\\src\\main\\resources\\static\\AdminFanZone\\Slike\\zvanicniOglas"+System.currentTimeMillis()+".jpg";
					decoder(officialAd.getImage(), pathFile);
					String splitPath[] = pathFile.split("static\\\\");
					officialAd.setImage(splitPath[1]);
				}
			}
		}
		
		
		return officialAdRepository.save(officialAd);
	}

	@Override
	public OfficialAd findOne(Long id) {
		return officialAdRepository.findOne(id);
	}

	@Override
	public OfficialAd delete(Long id) {
		OfficialAd officialAd = officialAdRepository.findOne(id);
		if(officialAd == null){
			throw new IllegalArgumentException("Tried to delete"
					+ "non-existant userAd");
		}
		officialAdRepository.delete(officialAd);
		return officialAd;
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
