package isa.projekat.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import isa.projekat.domain.Auditorium;


@Repository
public interface AuditoriumRepository extends JpaRepository<Auditorium, Long> {

}
