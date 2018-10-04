package org.thesis.roulett.view;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.thesis.roulett.model.Player;
import org.thesis.roulett.repository.PlayerRepository;

@Component
@ViewScoped
public class HomePageView extends BaseView {

	@Autowired
	private PlayerRepository repository;
	
	private Player loggedInPlayer;
	
	public HomePageView() {
		super();
	}
	
	@PostConstruct
	public void init() {
		loggedInPlayer = repository.findById(LoginRegisterView.getId()).get();
//				LoginRegisterView.getPlayer();
		
		System.out.println(loggedInPlayer.toString());
	}

	public Player getLoggedInPlayer() {
		return loggedInPlayer;
	}

	public void setLoggedInPlayer(Player loggedInPlayer) {
		this.loggedInPlayer = loggedInPlayer;
	}
	
}
