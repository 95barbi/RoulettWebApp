package org.thesis.roulett.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.thesis.roulett.model.Game;

@Repository
public interface GameRepository extends JpaRepository<Game, Long> {

}
