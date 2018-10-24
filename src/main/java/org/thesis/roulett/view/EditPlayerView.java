package org.thesis.roulett.view;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.thesis.roulett.model.Player;
import org.thesis.roulett.repository.PlayerRepository;
import org.thesis.roulett.service.PlayerService;

@Component
@ViewScoped
public class EditPlayerView extends BaseView {
	
//	@Autowired
//	private PlayerRepository repository;
	
	@Autowired
	private PlayerService service;

	private Player player;

	public EditPlayerView() {
		super();
	}

	@PostConstruct
	public void init() {
		player = flashGet("selectedPlayer");
	}

	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}

	public String save() {
//		repository.save(player);
		service.updatePlayer(player);
		
		return this.redirect("getPlayers");
	}
}
