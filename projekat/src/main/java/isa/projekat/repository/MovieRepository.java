package isa.projekat.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import isa.projekat.domain.Movie;

public interface MovieRepository extends JpaRepository<Movie, Long> {

}
