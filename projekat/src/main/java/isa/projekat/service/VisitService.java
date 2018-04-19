package isa.projekat.service;

import java.util.List;

import isa.projekat.domain.Visit;

public interface VisitService {
	
	Visit save(Visit visit);
	
	List<Visit> findAll();
	
	Visit findOne(Long id);
}
