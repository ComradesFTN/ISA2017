package isa.projekat.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import isa.projekat.domain.Show;

@Repository
public interface ShowRepository extends JpaRepository<Show, Long> {

}