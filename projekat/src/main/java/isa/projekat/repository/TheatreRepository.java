package isa.projekat.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import isa.projekat.domain.Theatre;

@Repository
public interface TheatreRepository extends JpaRepository<Theatre, Long> {

}
