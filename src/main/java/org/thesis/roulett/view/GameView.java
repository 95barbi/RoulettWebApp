package org.thesis.roulett.view;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.thesis.roulett.repository.GameRepository;
import org.thesis.roulett.repository.PlayerRepository;

public class GameView {
	
	@Autowired
	private PlayerRepository playerRepository;
	
	@Autowired
	private GameRepository gameRepository;
	
	private boolean isDisabled = true;
	
	private int bet;
	
	public GameView() {
		super();
	}
	
	@PostConstruct
	public void init() {
		
	}

	public int getBet() {
		return bet;
	}

	public void setBet(int bet) {
		this.bet = bet;
	}

	public boolean getIsDisabled() {
		return isDisabled;
	}

	public void setIsDisabled(boolean isDisabled) {
		this.isDisabled = isDisabled;
	}
	
	public String play() {
		return null;
	}
	
	public void enableNumbers() {
		isDisabled = false;
	}
	
}
