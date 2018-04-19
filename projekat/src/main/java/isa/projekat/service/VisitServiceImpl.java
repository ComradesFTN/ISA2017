package isa.projekat.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import isa.projekat.domain.Visit;
import isa.projekat.repository.VisitRepository;

@Service
public class VisitServiceImpl implements VisitService {
	
	@Autowired
	VisitRepository visitRepository;
	
	@Override
	public Visit save(Visit visit) {
		return visitRepository.save(visit);
	}

	@Override
	public List<Visit> findAll() {
		return visitRepository.findAll();
	}

	@Override
	public Visit findOne(Long id) {
		return visitRepository.findOne(id);
	}

}
