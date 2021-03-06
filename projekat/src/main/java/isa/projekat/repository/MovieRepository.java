package isa.projekat.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import isa.projekat.domain.Movie;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Long> {

}
