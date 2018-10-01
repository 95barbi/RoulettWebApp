package org.elte.hu.thesis.roulett.view;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;

import org.elte.hu.thesis.roulett.model.Player;
import org.elte.hu.thesis.roulett.repository.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@ViewScoped
public class EditPlayerView extends BaseView {

	private Player player;
	
	@Autowired
	private PlayerRepository repository;

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
		repository.save(player);
		
		return this.redirect("getPlayers");
	}
}
