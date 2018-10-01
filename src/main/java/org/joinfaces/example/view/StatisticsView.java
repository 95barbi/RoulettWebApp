package org.joinfaces.example.view;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;

import org.joinfaces.example.model.Game;
import org.joinfaces.example.model.Player;
import org.joinfaces.example.repository.GameRepository;
import org.joinfaces.example.repository.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@ViewScoped
public class StatisticsView extends BaseView {

	@Autowired
	private GameRepository gameRepository;

	@Autowired
	private PlayerRepository playerRepository;

	private List<Game> games;
	private List<Player> players;

	private int totalLoss;
	private int totalProfit;

	private Player biggestLooser;
	private Player biggestWinner;
	
	private int biggestLoss;
	private int biggestProfit;

	private int bank;

	private boolean isProfitable;

	public StatisticsView() {
		super();
	}

	@PostConstruct
	public void init() {
		games = gameRepository.findAll();
		players = playerRepository.findAll();

		bank = 100000;

		totalLoss = 0;
		totalProfit = 0;
		
		biggestLoss = 0;
		biggestProfit = 0;
		
		countTotalLost();
		countTotalWon();
		
		searchForBiggestLooser();
		searchForBiggestWinner();
		
		countIsProfitable();
	}

	private void countTotalLost() {
		for (Game g : games) {
			if (g.getIsLost() == true) {
				totalLoss += g.getBet();
			}
		}
	}

	private void countTotalWon() {
		for (Game g : games) {
			if (g.getIsLost() == false) {
				totalProfit += g.getBet();
			}
		}
	}

	private void searchForBiggestLooser() {
		biggestLooser = new Player();
		
		for (Game g : games) {
			if (g.getIsLost() == true) {
				if (g.getBet() > biggestLoss) {
					biggestLoss = g.getBet();
					biggestLooser = g.getPlayer();
				}
			}
		}
	}

	private void searchForBiggestWinner() {
		biggestWinner = new Player();
		
		for (Game g : games) {
			if (g.getIsLost() == false) {
				if (g.getBet() > biggestProfit) {
					biggestProfit = g.getBet();
					biggestWinner = g.getPlayer();
				}
			}
		}
	}

	private void countIsProfitable() {
		bank += totalProfit;
		bank -= totalLoss;
		
		if (bank > 0) {
			isProfitable = true;
		} else {
			isProfitable = false;
		}
	}

	public GameRepository getGameRepository() {
		return gameRepository;
	}

	public void setGameRepository(GameRepository gameRepository) {
		this.gameRepository = gameRepository;
	}

	public PlayerRepository getPlayerRepository() {
		return playerRepository;
	}

	public void setPlayerRepository(PlayerRepository playerRepository) {
		this.playerRepository = playerRepository;
	}

	public List<Game> getGames() {
		return games;
	}

	public void setGames(List<Game> games) {
		this.games = games;
	}

	public List<Player> getPlayers() {
		return players;
	}

	public void setPlayers(List<Player> players) {
		this.players = players;
	}

	public int getTotalLoss() {
		return totalLoss;
	}

	public void setTotalLoss(int totalLoss) {
		this.totalLoss = totalLoss;
	}

	public int getTotalProfit() {
		return totalProfit;
	}

	public void setTotalProfit(int totalProfit) {
		this.totalProfit = totalProfit;
	}

	public Player getBiggestLooser() {
		return biggestLooser;
	}

	public void setBiggestLooser(Player biggestLooser) {
		this.biggestLooser = biggestLooser;
	}

	public Player getBiggestWinner() {
		return biggestWinner;
	}

	public void setBiggestWinner(Player biggestWinner) {
		this.biggestWinner = biggestWinner;
	}

	public int getBiggestLoss() {
		return biggestLoss;
	}

	public void setBiggestLoss(int biggestLoss) {
		this.biggestLoss = biggestLoss;
	}

	public int getBiggestProfit() {
		return biggestProfit;
	}

	public void setBiggestProfit(int biggestProfit) {
		this.biggestProfit = biggestProfit;
	}

	public int getBank() {
		return bank;
	}

	public void setBank(int bank) {
		this.bank = bank;
	}

	public boolean getIsProfitable() {
		return isProfitable;
	}

	public void setProfitable(boolean isProfitable) {
		this.isProfitable = isProfitable;
	}

	@Override
	public String toString() {
		return "StatisticsView [gameRepository=" + gameRepository + ", playerRepository=" + playerRepository
				+ ", games=" + games + ", players=" + players + ", totalLoss=" + totalLoss + ", totalProfit="
				+ totalProfit + ", biggestLooser=" + biggestLooser + ", biggestWinner=" + biggestWinner
				+ ", biggestLoss=" + biggestLoss + ", biggestProfit=" + biggestProfit + ", bank=" + bank
				+ ", isProfitable=" + isProfitable + "]";
	}
	
}
