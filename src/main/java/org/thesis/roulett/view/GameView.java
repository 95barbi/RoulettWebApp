package org.thesis.roulett.view;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.faces.application.FacesMessage;
import javax.faces.view.ViewScoped;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.thesis.roulett.dto.BallBean;
import org.thesis.roulett.model.Game;
import org.thesis.roulett.model.Player;
import org.thesis.roulett.repository.GameRepository;
import org.thesis.roulett.repository.PlayerRepository;
import org.thesis.roulett.service.GameService;
import org.thesis.roulett.service.PlayerService;

@Component
@ViewScoped
public class GameView extends BaseView {

	@Autowired
	private PlayerService playerService;

	@Autowired
	private GameService gameService;

	// http://mek.niif.hu/00000/00056/html/139.htm
	private List<Integer> numbers; // ...
	private List<String> doubleNumbers; // 1-2
	private List<String> tripleNumbers; // 7-8-9
	private List<String> quadrupleNumbers; // 22-23-25-26
	private List<String> sixfoldNumbers; // 4-5-6-7-8-9
	private List<String> columns; // first, second, third
	private List<String> dozens; // 1-12, 13-24, 25-36
	private List<String> colours; // red,black
	private List<String> parities; // 1,3...
	private List<String> halfs; // 1-18

	private String[] selectedNumbers;//-
	private String[] selectedDoubleNumbers;//-
	private String[] selectedTripleNumbers;//-
	private String[] selectedQuadrupleNumbers;//-
	private String[] selectedSixfoldNumbers;//-
	private String[] selectedColumns;//-
	private String[] selectedDozens;//-
	private String[] selectedColours;//-
	private String[] selectedParities;
	private String[] selectedHalfs;
	
	private List<Integer> firstColumn;
	private List<Integer> secondColumn;
	private List<Integer> thirdColumn;

	private int bet;
	private int ballNumber;
	private String ballColour;
	private int totalBet;

	private int won;
	private int lost;

	private Player loggedInPlayer;
	private int oldBalance;
	private BallBean ball;
	
	private List<String> goodTips;
	private List<String> badTips;

	public GameView() {
		super();
		numbers = new ArrayList<>();
		doubleNumbers = new ArrayList<>();
		tripleNumbers = new ArrayList<>();
		quadrupleNumbers = new ArrayList<>();
		sixfoldNumbers = new ArrayList<>();
		columns = Arrays.asList("First", "Second", "Third");
		dozens = Arrays.asList("1-12", "13-24", "25-36");
		colours = Arrays.asList("Red", "Black");
		parities = Arrays.asList("Even", "Odd");
		halfs = Arrays.asList("1-18", "19-36");
		
		goodTips = new ArrayList<>();
		badTips = new ArrayList<>();
	}

	@PostConstruct
	public void init() {
		won = 0;
		lost = 0;
		oldBalance = 0;
		ball = new BallBean();
		
		loggedInPlayer = playerService.getLoggedInPlayer();
		
		for (int i = 0; i <= 36; ++i) {
			numbers.add(i);
		}

		for (int i = 1; i < 36; ++i) {
			if (i % 3 != 0) {
				doubleNumbers.add(i + "-" + (i + 1));
			}

			if (i < 34) {
				doubleNumbers.add(i + "-" + (i + 3));
			}
		}

		for (int i = 1; i <= 34; i = i + 3) {
			tripleNumbers.add(i + "-" + (i + 1) + "-" + (i + 2));
		}

		for (int i = 1; i <= 31; i = i + 3) {
			sixfoldNumbers.add(i + "-" + (i + 1) + "-" + (i + 2) + "-" + (i + 3) + "-" + (i + 4) + "-" + (i + 5));
		}

		for (int i = 1; i <= 32; ++i) {
			if (i % 3 != 0) {
				quadrupleNumbers.add(i + "-" + (i + 1) + "-" + (i + 3) + "-" + (i + 4));
			}
		}
		
		firstColumn = Arrays.asList(1,4,7,10,13,16,19,22,25,28,31,34);
		secondColumn = Arrays.asList(2,5,8,11,14,17,20,23,26,29,32,35);
		thirdColumn = Arrays.asList(3,6,9,12,15,18,21,24,27,30,33,36);
		
	}

	public String play() {
		System.out.println("----------------------------");
		
		printSelected();
		ball.spin();
		
		ballColour = ball.getColour();
		ballNumber = ball.getNumber();
		
		int totalBet = countSelected() * bet;

		System.out.println("Total bet: " + totalBet);
		System.out.println("ball: " + ball.getNumber() + ", " + ball.getColour());

		if (totalBet > loggedInPlayer.getBalance()) {
			this.error("Overbet!", "Your total bet is more than your balance");
			return null;
		}
		
		oldBalance = loggedInPlayer.getBalance();
		
		System.out.println("oldbalance: " + loggedInPlayer.getBalance());
		loggedInPlayer.decreaseBalance(totalBet);
		System.out.println("minusbalance: " + loggedInPlayer.getBalance());
		
		for(String s: selectedNumbers) {
			if (s.equals(ball.getNumber())) {
//				loggedInPlayer.increaseBalance(bet*35);
				won += (bet*35);
				goodTips.add(s);
				System.out.println("NUMBER: " + s +", "+ ball.getNumber());
			} else {
				badTips.add(s);
			}
		}
		
		for (String s : selectedDoubleNumbers) {
			String[] numbers = s.split("-");
			
			for(String n : numbers) {
				if (ball.getNumber() == Integer.parseInt(n)) {
//					loggedInPlayer.increaseBalance(bet*17);
					won += (bet*17);
					goodTips.add(s);
					System.out.println("DOUBLE: "+ s +", "+ ball.getNumber());
				} else {
					badTips.add(s);
				}
			}
		}
		
		for(String s : selectedTripleNumbers) {
			String[] numbers = s.split("-");
			
			for(String n : numbers) {
				if (ball.getNumber() == Integer.parseInt(n)) {
//					loggedInPlayer.increaseBalance(bet*11);
					won += (bet*11);
					goodTips.add(s);
					System.out.println("TRIPLE: "+ s +", "+ ball.getNumber());
				} else {
					badTips.add(s);
				}
			}
		}
		
		for(String s : selectedQuadrupleNumbers) {
			String[] numbers = s.split("-");
			
			for(String n : numbers) {
				if (ball.getNumber() == Integer.parseInt(n)) {
//					loggedInPlayer.increaseBalance(bet*8);
					won += (bet*8);
					goodTips.add(s);
					System.out.println("QUADRUPLE: "+ s +", "+ ball.getNumber());
				} else {
					badTips.add(s);
				}
			}
		}
		
		for(String s : selectedSixfoldNumbers) {
			String[] numbers = s.split("-");
			
			for(String n : numbers) {
				if (ball.getNumber() == Integer.parseInt(n)) {
//					loggedInPlayer.increaseBalance(bet*5);
					won += (bet*5);
					goodTips.add(s);
					System.out.println("SIXFOLD: "+ s +", "+ ball.getNumber());
				} else {
					badTips.add(s);
				}
			}
		}
		
		for(String s : selectedColumns) {
			if (s.equals("First")) {
				if (firstColumn.contains(ball.getNumber())) {
//					loggedInPlayer.increaseBalance(bet*2);
					won += (bet*2);
					goodTips.add(s);
					System.out.println("FIRSTCOLUMN: "+ s +", "+ ball.getNumber());
				} else {
					badTips.add(s);
				}
			} else if (s.equals("Second")) {
				if (secondColumn.contains(ball.getNumber())) {
//					loggedInPlayer.increaseBalance(bet*2);
					won += (bet*2);
					goodTips.add(s);
					System.out.println("SECONDCOLUMN: "+ s +", "+ ball.getNumber());
				} else {
					badTips.add(s);
				}
			} else {
				if (thirdColumn.contains(ball.getNumber())) {
//					loggedInPlayer.increaseBalance(bet*2);
					won += (bet*2);
					goodTips.add(s);
					System.out.println("THIRDCOLUMN: "+ s +", "+ ball.getNumber());
				} else {
					badTips.add(s);
				}
			}
		}
		
		for(String s : selectedDozens) {
			String[] numbers = s.split("-");
			
			if (Integer.parseInt(numbers[0]) < ball.getNumber() && Integer.parseInt(numbers[1]) > ball.getNumber()) {
//				loggedInPlayer.increaseBalance(bet*2);
				won += (bet*2);
				goodTips.add(s);
				System.out.println("DOZENS: "+ s +", "+ ball.getNumber());
			} else {
				badTips.add(s);
			}
		}
		
		for(String s : selectedColours) {
			if (ball.getColour().equals(s)) {
//				loggedInPlayer.increaseBalance(bet*2);
				won += (bet*2);
				goodTips.add(s);
				System.out.println("COLOUR: "+ s +", "+ ball.getColour());
			} else {
				badTips.add(s);
			}
		}
		
		for(String s : selectedParities) {
			if (s.equals("Even") && ball.getNumber()%2==0) {
//				loggedInPlayer.increaseBalance(bet*2);
				won += (bet*2);
				goodTips.add(s);
				System.out.println("PARITIES: "+ s +", "+ ball.getNumber());
			} else if (s.equals("Odd") && ball.getNumber()%2==1) {
//				loggedInPlayer.increaseBalance(bet*2);
				won += (bet*2);
				goodTips.add(s);
				System.out.println("PARITIES: "+ s +", "+ ball.getNumber());
			} else {
				badTips.add(s);
			}
		}
		
		for(String s : selectedHalfs) {
			String[] numbers = s.split("-");
			
			if (Integer.parseInt(numbers[0]) < ball.getNumber() && Integer.parseInt(numbers[1]) > ball.getNumber()) {
//				loggedInPlayer.increaseBalance(bet*2);
				won += (bet*2);
				goodTips.add(s);
				System.out.println("HALFS: "+ s +", "+ ball.getNumber());
			} else {
				badTips.add(s);
			}
		}
		

		loggedInPlayer.increaseBalance(won);
		
		System.out.println("newbalance: " + loggedInPlayer.getBalance());
		
		
		lost = totalBet;
		boolean isLost = true;
		
		if (won > lost) {
			lost = 0;
			isLost = false;
		} else {
			lost -= won;
		}
		
		this.flashPut("won", won);
		this.flashPut("lost", lost);
		this.flashPut("totalBet", totalBet);
		this.flashPut("bet", bet);
		this.flashPut("oldBalance", oldBalance);
		this.flashPut("ballColour", ballColour);
		this.flashPut("ballNumber", ballNumber);
		this.flashPut("goodTips", goodTips);
		this.flashPut("badTips", badTips);
		
		playerService.updatePlayer(loggedInPlayer);

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();
		
		Game newGame = new Game(loggedInPlayer.getName(), sdf.format(date), isLost, totalBet, loggedInPlayer);
		gameService.save(newGame);
		
		return this.redirect("gameOutcome");
	}

	public int countSelected() {
		return (selectedNumbers.length + selectedDoubleNumbers.length + selectedTripleNumbers.length + selectedQuadrupleNumbers.length + selectedSixfoldNumbers.length + selectedColumns.length + selectedDozens.length + selectedColours.length + selectedParities.length + selectedHalfs.length);
	}

	public void printSelected() {
		System.out.println("Bet: " + bet);
		System.out.println("Selected:");

		for (String s : selectedNumbers) {
			System.out.println(s);
		}

		for (String s : selectedDoubleNumbers) {
			System.out.println(s);
		}

		for (String s : selectedTripleNumbers) {
			System.out.println(s);
		}

		for (String s : selectedQuadrupleNumbers) {
			System.out.println(s);
		}

		for (String s : selectedSixfoldNumbers) {
			System.out.println(s);
		}

		for (String s : selectedColumns) {
			System.out.println(s);
		}

		for (String s : selectedDozens) {
			System.out.println(s);
		}

		for (String s : selectedColours) {
			System.out.println(s);
		}

		for (String s : selectedParities) {
			System.out.println(s);
		}

		for (String s : selectedHalfs) {
			System.out.println(s);
		}
	}

	// getters, setters
	public int getBet() {
		return bet;
	}

	public void setBet(int bet) {
		this.bet = bet;
	}

	public List<Integer> getNumbers() {
		return numbers;
	}

	public void setNumbers(List<Integer> numbers) {
		this.numbers = numbers;
	}

	public List<String> getDoubleNumbers() {
		return doubleNumbers;
	}

	public void setDoubleNumbers(List<String> doubleNumbers) {
		this.doubleNumbers = doubleNumbers;
	}

	public String[] getSelectedNumbers() {
		return selectedNumbers;
	}

	public void setSelectedNumbers(String[] selectedNumbers) {
		this.selectedNumbers = selectedNumbers;
	}

	public String[] getSelectedDoubleNumbers() {
		return selectedDoubleNumbers;
	}

	public void setSelectedDoubleNumbers(String[] selectedDoubleNumbers) {
		this.selectedDoubleNumbers = selectedDoubleNumbers;
	}

	public List<String> getTripleNumbers() {
		return tripleNumbers;
	}

	public void setTripleNumbers(List<String> tripleNumbers) {
		this.tripleNumbers = tripleNumbers;
	}

	public List<String> getQuadrupleNumbers() {
		return quadrupleNumbers;
	}

	public void setQuadrupleNumbers(List<String> quadrupleNumbers) {
		this.quadrupleNumbers = quadrupleNumbers;
	}

	public List<String> getSixfoldNumbers() {
		return sixfoldNumbers;
	}

	public void setSixfoldNumbers(List<String> sixfoldNumbers) {
		this.sixfoldNumbers = sixfoldNumbers;
	}

	public List<String> getColumns() {
		return columns;
	}

	public void setColumns(List<String> columns) {
		this.columns = columns;
	}

	public List<String> getDozens() {
		return dozens;
	}

	public void setDozens(List<String> dozens) {
		this.dozens = dozens;
	}

	public List<String> getColours() {
		return colours;
	}

	public void setColours(List<String> colours) {
		this.colours = colours;
	}

	public List<String> getParities() {
		return parities;
	}

	public void setParities(List<String> parities) {
		this.parities = parities;
	}

	public List<String> getHalfs() {
		return halfs;
	}

	public void setHalfs(List<String> halfs) {
		this.halfs = halfs;
	}

	public String[] getSelectedTripleNumbers() {
		return selectedTripleNumbers;
	}

	public void setSelectedTripleNumbers(String[] selectedTripleNumbers) {
		this.selectedTripleNumbers = selectedTripleNumbers;
	}

	public String[] getSelectedQuadrupleNumbers() {
		return selectedQuadrupleNumbers;
	}

	public void setSelectedQuadrupleNumbers(String[] selectedQuadrupleNumbers) {
		this.selectedQuadrupleNumbers = selectedQuadrupleNumbers;
	}

	public String[] getSelectedSixfoldNumbers() {
		return selectedSixfoldNumbers;
	}

	public void setSelectedSixfoldNumbers(String[] selectedSixfoldNumbers) {
		this.selectedSixfoldNumbers = selectedSixfoldNumbers;
	}

	public String[] getSelectedColumns() {
		return selectedColumns;
	}

	public void setSelectedColumns(String[] selectedColumns) {
		this.selectedColumns = selectedColumns;
	}

	public String[] getSelectedDozens() {
		return selectedDozens;
	}

	public void setSelectedDozens(String[] selectedDozens) {
		this.selectedDozens = selectedDozens;
	}

	public String[] getSelectedColours() {
		return selectedColours;
	}

	public void setSelectedColours(String[] selectedColours) {
		this.selectedColours = selectedColours;
	}

	public String[] getSelectedParities() {
		return selectedParities;
	}

	public void setSelectedParities(String[] selectedParities) {
		this.selectedParities = selectedParities;
	}

	public String[] getSelectedHalfs() {
		return selectedHalfs;
	}

	public void setSelectedHalfs(String[] selectedHalfs) {
		this.selectedHalfs = selectedHalfs;
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
	
}
