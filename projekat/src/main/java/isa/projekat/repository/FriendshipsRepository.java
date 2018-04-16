package isa.projekat.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import isa.projekat.domain.Friendships;

@Repository
public interface FriendshipsRepository extends JpaRepository<Friendships, Long> {

}
