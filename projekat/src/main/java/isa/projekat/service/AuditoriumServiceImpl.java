package isa.projekat.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import isa.projekat.domain.Auditorium;
import isa.projekat.repository.AuditoriumRepository;

@Service
public class AuditoriumServiceImpl implements AuditoriumService {

	@Autowired
	private AuditoriumRepository auditoriumRepository;
	
	@Override
	public List<Auditorium> findAll() {
		return auditoriumRepository.findAll();
	}

	@Override
	public Auditorium save(Auditorium auditorium) {
		return auditoriumRepository.save(auditorium);
	}

	@Override
	public Auditorium findOne(Long id) {
		return auditoriumRepository.findOne(id);
	}

}
