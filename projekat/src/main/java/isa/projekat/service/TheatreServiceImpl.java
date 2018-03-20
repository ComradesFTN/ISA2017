package isa.projekat.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import isa.projekat.domain.Theatre;
import isa.projekat.repository.TheatreRepository;

@Service
public class TheatreServiceImpl implements TheatreService {

	@Autowired
	private TheatreRepository theatreRepository;
	
	@Override
	public List<Theatre> findAll() {
		return theatreRepository.findAll();
	}

	@Override
	public Theatre save(Theatre theatre) {
		return theatreRepository.save(theatre);	
	}

}
