package org.thesis.roulett.service;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.thesis.roulett.model.Player;
import org.thesis.roulett.repository.PlayerRepository;

@Service
public class PlayerService implements Serializable {

	@Autowired
	private PlayerRepository repository;
	
	private Player loggedInPlayer;
	
	public List<Player> getAllGames() {
		return repository.findAll();
	}
	
	public Player getGameById(Long id) {
		return repository.findById(id).get();
	}

	public void login(Player player) {
		loggedInPlayer = player;
	}
	
	public Player getLoggedInPlayer() {
		return loggedInPlayer; 
	}
	
	public void addPlayer(Player player) {
		repository.save(player);
	}
	
	public void updatePlayer(Player player) {
		repository.save(player);
	}
	
	public List<Player> getAllUsers() {
		return repository.findUsers();
	}
	
	public void deleteUser(Player player) {
		repository.delete(player);
	}
	
	public Player getUserIfRegistered(String username, String password) {
		return repository.findPlayerIfRegistered(username, password);
	}
}
