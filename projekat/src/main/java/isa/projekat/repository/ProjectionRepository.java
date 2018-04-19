package isa.projekat.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import isa.projekat.domain.Movie;
import isa.projekat.domain.Projection;

@Repository
public interface ProjectionRepository extends JpaRepository<Projection, Long> {

	Projection findByMovie_idAndDateAndAuditoriumIdAndTerm(long movie_id,Date date,long auditoriumId,String term);
	
	List<Projection> findByMovie_id(long movie_id);
	
}
