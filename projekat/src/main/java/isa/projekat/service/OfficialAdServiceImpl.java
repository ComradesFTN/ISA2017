package isa.projekat.service;

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

}
