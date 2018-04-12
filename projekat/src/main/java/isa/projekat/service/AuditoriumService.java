package isa.projekat.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import isa.projekat.domain.Auditorium;
import isa.projekat.repository.AuditoriumRepository;

@Service
public class AuditoriumService {
	
	@Autowired
	private AuditoriumRepository auditoriumRepository;
	
	public Auditorium save(Auditorium auditorium) {
		return auditoriumRepository.save(auditorium);
	}

}
