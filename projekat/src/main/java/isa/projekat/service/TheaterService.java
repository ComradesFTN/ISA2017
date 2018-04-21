package isa.projekat.service;

import java.util.List;

import isa.projekat.domain.Theater;

public interface TheaterService {

	List<Theater> findAll();

	Theater save(Theater theater);

	Theater findOne(Long id);

	void refresh(Theater theater);

}
