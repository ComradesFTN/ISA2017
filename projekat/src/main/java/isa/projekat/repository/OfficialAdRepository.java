package isa.projekat.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import isa.projekat.domain.OfficialAd;

@Repository
public interface OfficialAdRepository  extends JpaRepository<OfficialAd, Long> {


}
