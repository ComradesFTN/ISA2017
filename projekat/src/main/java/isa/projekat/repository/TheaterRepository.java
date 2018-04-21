package isa.projekat.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import isa.projekat.domain.Theater;

@Repository
public interface TheaterRepository extends JpaRepository<Theater, Long> {

}
