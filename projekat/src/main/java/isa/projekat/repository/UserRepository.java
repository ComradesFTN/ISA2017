package isa.projekat.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import isa.projekat.domain.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{

}
