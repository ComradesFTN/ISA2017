package isa.projekat.service;

import java.util.List;

import isa.projekat.domain.Theatre;

public interface TheatreService {

	List<Theatre> findAll();

	Theatre save(Theatre theatre);
}
