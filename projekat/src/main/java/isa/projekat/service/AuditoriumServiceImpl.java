package isa.projekat.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import isa.projekat.domain.Auditorium;
import isa.projekat.domain.Projection;
import isa.projekat.repository.AuditoriumRepository;
import isa.projekat.repository.ProjectionRepository;

@Service
public class AuditoriumServiceImpl implements AuditoriumService {

	@Autowired
	private AuditoriumRepository auditoriumRepository;
	
	@Autowired
	private ProjectionRepository projectionRepository;
	
	@Override
	public List<Auditorium> findAll() {
		return auditoriumRepository.findAll();
	}

	@Override
	public Auditorium saveAndEditSeats(Auditorium auditorium) {
		Auditorium savedAuditorium = auditoriumRepository.save(auditorium);
		System.out.println(savedAuditorium.getSeats().size());
		List<Projection> projections = projectionRepository.findAll();
		for(Projection projection : projections){
			if(projection.getAuditoriumId()==savedAuditorium.getId()){
				for(int i=0;i<projection.getSeats().size();i++){
					if(projection.getSeats().get(i)!=2){
						projection.getSeats().set(i, savedAuditorium.getSeats().get(i));				
					}
				}
				projectionRepository.save(projection);
			}
		}
		return savedAuditorium;
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
