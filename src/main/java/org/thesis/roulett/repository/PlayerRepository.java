package org.thesis.roulett.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.thesis.roulett.model.Player;

@Repository
public interface PlayerRepository extends JpaRepository<Player, Long> {

    @Query("from Player where username = :username and password = :password")
    Player findPlayerIfRegistered(@Param("username") String username, @Param("password") String password);

    @Query("from Player where role = 'USER'")
    List<Player> findUsers();
    
}
