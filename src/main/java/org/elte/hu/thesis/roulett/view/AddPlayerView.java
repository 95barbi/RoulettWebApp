package org.elte.hu.thesis.roulett.view;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;

import org.elte.hu.thesis.roulett.model.Player;
import org.elte.hu.thesis.roulett.repository.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@ViewScoped
public class AddPlayerView extends BaseView {

	private Player player;
	
	@Autowired
	private PlayerRepository repository;
	
	public AddPlayerView() {
		super();
	}
	
	@PostConstruct
	public void init() {
		player = new Player();
		
		player.setRole("USER");
	}

	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}
	
	public String save() {
		repository.save(player);
		
		return this.redirect("getPlayers");
	}
	
}
