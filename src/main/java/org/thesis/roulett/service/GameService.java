package org.thesis.roulett.service;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.thesis.roulett.repository.GameRepository;
import org.thesis.roulett.model.Game;

@Service
public class GameService implements Serializable {

	@Autowired
	private GameRepository repository;
	
	public List<Game> getAllGames() {
		return repository.findAll();
	}
	
	public Game getGameById(Long id) {
		return repository.findById(id).get();
	}
	
	public void save(Game game) {
		repository.save(game);
	}

}
