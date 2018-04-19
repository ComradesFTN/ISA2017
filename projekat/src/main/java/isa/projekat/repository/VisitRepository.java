package isa.projekat.repository;

import org.springframework.stereotype.Repository;

import isa.projekat.domain.Visit;

import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface VisitRepository extends JpaRepository<Visit, Long>{

}
