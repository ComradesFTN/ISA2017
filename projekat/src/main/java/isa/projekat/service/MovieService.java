package isa.projekat.service;

import java.util.List;

import isa.projekat.domain.Movie;

public interface MovieService {
	
	List<Movie> findAll();

	Movie save(Movie movie);

	Movie findOne(Long id);

	Movie delete(Long id);

}
