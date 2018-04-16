package isa.projekat.service;

import java.util.List;

import isa.projekat.domain.Auditorium;

public interface AuditoriumService {

	List<Auditorium> findAll();

	Auditorium save(Auditorium auditorium);

	Auditorium findOne(Long id);
	
}
