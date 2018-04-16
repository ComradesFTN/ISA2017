package isa.projekat.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import isa.projekat.domain.Projection;

@Repository
public interface ProjectionRepository extends JpaRepository<Projection, Long> {

}
