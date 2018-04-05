package isa.projekat.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import isa.projekat.domain.UserAd;
import isa.projekat.repository.UserAdRepository;

@Service
public class UserAdServiceImpl implements UserAdService{

	@Autowired
	public UserAdRepository userAdRepository; 
	
	@Override
	public List<UserAd> findAll() {
		return userAdRepository.findAll();
	}

	@Override
	public UserAd save(UserAd userAd) {
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
	

}
