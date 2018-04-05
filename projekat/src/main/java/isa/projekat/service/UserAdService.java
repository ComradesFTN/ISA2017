package isa.projekat.service;

import java.util.List;

import isa.projekat.domain.UserAd;


public interface UserAdService {

	List<UserAd> findAll();

	UserAd save(UserAd userAd);

	UserAd findOne(Long id);

	UserAd delete(Long id);
	
}
