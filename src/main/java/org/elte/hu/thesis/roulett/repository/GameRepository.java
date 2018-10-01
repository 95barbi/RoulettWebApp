package org.elte.hu.thesis.roulett.repository;

import org.elte.hu.thesis.roulett.model.Game;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GameRepository extends JpaRepository<Game, Long>{

}
