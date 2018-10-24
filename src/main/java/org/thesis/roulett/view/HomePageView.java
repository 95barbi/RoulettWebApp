package org.thesis.roulett.view;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.thesis.roulett.model.Player;
import org.thesis.roulett.repository.PlayerRepository;
import org.thesis.roulett.service.GameService;
import org.thesis.roulett.service.PlayerService;

@Component
@ViewScoped
public class HomePageView extends BaseView {

//	@Autowired
//	private PlayerRepository repository;
	
	@Autowired
	private PlayerService service;
	
	private Player loggedInPlayer;
	
	public HomePageView() {
		super();
	}
	
	@PostConstruct
	public void init() {
//		loggedInPlayer = repository.findById(LoginRegisterView.getId()).get();
//				LoginRegisterView.getPlayer();
		loggedInPlayer = service.getLoggedInPlayer();
	}

	public Player getLoggedInPlayer() {
		return loggedInPlayer;
	}

	public void setLoggedInPlayer(Player loggedInPlayer) {
		this.loggedInPlayer = loggedInPlayer;
	}
	
}
