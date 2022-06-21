package dev.crawford.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import dev.crawford.model.Talk;

@Repository
public interface TalkRepository extends JpaRepository<Talk, Integer>{
    
}
