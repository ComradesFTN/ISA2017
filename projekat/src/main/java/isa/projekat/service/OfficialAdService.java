package isa.projekat.service;

import java.util.List;

import isa.projekat.domain.OfficialAd;

public interface OfficialAdService {
	
	List<OfficialAd> findAll();

	OfficialAd save(OfficialAd officialAd);

	OfficialAd findOne(Long id);

	OfficialAd delete(Long id);

}
