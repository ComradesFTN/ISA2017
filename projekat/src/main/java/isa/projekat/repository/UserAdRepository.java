package isa.projekat.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import isa.projekat.domain.UserAd;

@Repository
public interface UserAdRepository extends JpaRepository<UserAd, Long>{

}
