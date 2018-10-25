package org.thesis.roulett.view;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.thesis.roulett.model.Player;
import org.thesis.roulett.service.PlayerService;

@Component
@ViewScoped
public class Outcome extends BaseView {

	@Autowired
	private PlayerService playerService;

	private int bet;
	private int ballNumber;
	private String ballColour;
	private int totalBet;

	private int won;
	private int lost;

	private Player loggedInPlayer;
	private int oldBalance;
	
	private List<String> goodTips;
	private List<String> badTips;
	
	public Outcome() {
		
	}
	
	@PostConstruct
	public void init() {
		loggedInPlayer = playerService.getLoggedInPlayer();
		won = flashGet("won");
		lost = flashGet("lost");
		totalBet = flashGet("totalBet");
		bet = flashGet("bet");
		oldBalance = flashGet("oldBalance");
		ballColour = flashGet("ballColour");
		ballNumber = flashGet("ballNumber");
		goodTips = flashGet("goodTips");
		setBadTips(flashGet("badTips"));
	}
	
	public String playAgain() {
		if (false) {
			return null;
		}
		
		return this.redirect("game");
	}

	public int getBet() {
		return bet;
	}

	public void setBet(int bet) {
		this.bet = bet;
	}

	public int getBallNumber() {
		return ballNumber;
	}

	public void setBallNumber(int ballNumber) {
		this.ballNumber = ballNumber;
	}

	public String getBallColour() {
		return ballColour;
	}

	public void setBallColour(String ballColour) {
		this.ballColour = ballColour;
	}

	public int getTotalBet() {
		return totalBet;
	}

	public void setTotalBet(int totalBet) {
		this.totalBet = totalBet;
	}

	public int getWon() {
		return won;
	}

	public void setWon(int won) {
		this.won = won;
	}

	public int getLost() {
		return lost;
	}

	public void setLost(int lost) {
		this.lost = lost;
	}

	public Player getLoggedInPlayer() {
		return loggedInPlayer;
	}

	public void setLoggedInPlayer(Player loggedInPlayer) {
		this.loggedInPlayer = loggedInPlayer;
	}

	public int getOldBalance() {
		return oldBalance;
	}

	public void setOldBalance(int oldBalance) {
		this.oldBalance = oldBalance;
	}

	public List<String> getGoodTips() {
		return goodTips;
	}

	public void setGoodTips(List<String> goodTips) {
		this.goodTips = goodTips;
	}

	public List<String> getBadTips() {
		return badTips;
	}

	public void setBadTips(List<String> badTips) {
		this.badTips = badTips;
	}
	
}
