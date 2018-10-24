package org.thesis.roulett.view;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;

import org.primefaces.event.SelectEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.thesis.roulett.model.Player;
import org.thesis.roulett.repository.PlayerRepository;
import org.thesis.roulett.service.PlayerService;

@Component
@ViewScoped
public class GetPlayersView extends BaseView {
	
//	@Autowired
//	private PlayerRepository repository;
	
	@Autowired
	private PlayerService service;

	private List<Player> players;
	private Player selectedPlayer;
	
	private Boolean isDisabled = true;
	
	public GetPlayersView() {
		super();
	}
	
	@PostConstruct
	public void init() {
//		players = repository.findUsers();
		players = service.getAllUsers();
	}

	public void onRowSelect(SelectEvent event) {
		info("Player Selected", ((Player) event.getObject()).getUsername());
	}
	
	public void onRowSelect() {
		isDisabled = false;
	}

	public List<Player> getPlayers() {
		return players;
	}

	public void setPlayers(List<Player> players) {
		this.players = players;
	}

	public Player getSelectedPlayer() {
		return selectedPlayer;
	}

	public void setSelectedPlayer(Player selectedPlayer) {
		this.selectedPlayer = selectedPlayer;
	}

	public Boolean getIsDisabled() {
		return isDisabled;
	}

	public void setIsDisabled(Boolean isDisabled) {
		this.isDisabled = isDisabled;
	}
	
	public void deletePlayer() {
//		repository.delete(selectedPlayer);
		
		service.deleteUser(selectedPlayer);
		players.remove(selectedPlayer);
	}
	
	public String editPlayer() {
		this.flashPut("selectedPlayer", selectedPlayer);
		return this.redirect("editPlayer");
	}
	
	public String addPlayer() {
		return this.redirect("addPlayer");
	}
	
}
