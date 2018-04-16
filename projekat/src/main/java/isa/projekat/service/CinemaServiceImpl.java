package isa.projekat.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import isa.projekat.domain.Cinema;
import isa.projekat.repository.CinemaRepository;

@Service
public class CinemaServiceImpl implements CinemaService {

	@Autowired
	private CinemaRepository cinemaRepository;

	@Override
	public List<Cinema> findAll() {
		return cinemaRepository.findAll();
	}

	@Override
	public Cinema save(Cinema cinema) {
		return cinemaRepository.save(cinema);
	}

	@Override
	public Cinema findOne(Long id) {
		return cinemaRepository.findOne(id);
	}	

}
