package isa.projekat.service;

import java.util.List;

import isa.projekat.domain.Projection;

public interface ProjectionService {

	List<Projection> findAll();

	Projection save(Projection projection);

	Projection findOne(Long id);
}
