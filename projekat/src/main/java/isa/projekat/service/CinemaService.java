package isa.projekat.service;

import java.util.List;

import isa.projekat.domain.Cinema;

public interface CinemaService {
	
	List<Cinema> findAll();

	Cinema save(Cinema cinema);

	Cinema findOne(Long id);
}
