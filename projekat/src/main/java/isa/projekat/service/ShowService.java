package isa.projekat.service;

import java.util.List;

import isa.projekat.domain.Show;

public interface ShowService {

	List<Show> findAll();

	Show save(Show show);

	Show findOne(Long id);

	Show delete(Long id);
	
}
