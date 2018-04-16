package isa.projekat.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import isa.projekat.domain.Projection;
import isa.projekat.repository.ProjectionRepository;

@Service
public class ProjectionServiceImpl implements ProjectionService{

	@Autowired
	private ProjectionRepository projectionRepository;
	
	@Override
	public List<Projection> findAll() {
		return projectionRepository.findAll();
	}

	@Override
	public Projection save(Projection projection) {
		return projectionRepository.save(projection);
	}

	@Override
	public Projection findOne(Long id) {
		return projectionRepository.findOne(id);
	}

}
