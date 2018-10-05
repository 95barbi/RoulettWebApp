package org.thesis.roulett.view;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.thesis.roulett.repository.GameRepository;
import org.thesis.roulett.repository.PlayerRepository;

@Component
@ViewScoped
public class GameView extends BaseView {

	@Autowired
	private PlayerRepository playerRepository;

	@Autowired
	private GameRepository gameRepository;

	// http://mek.niif.hu/00000/00056/html/139.htm
	private List<Integer> numbers; // ...
	private List<Integer> doubleNumbers; // 1-2
	private List<Integer> tripleNumbers; // 7-8-9
	private List<Integer> quadrupleNumbers; // 22-23-25-26
	private List<Integer> sixfoldNumbers; // 4-5-6-7-8-9
	private List<Integer> columns; // first, second, third
	private List<Integer> dozens; // 1-12, 13-24, 25-36
	private List<Integer> colours; // red,black
	private List<Integer> evenOrOddNumbers; // 1,3...
	private List<Integer> halfs; // 1-18
	
	private String[] selectedNumbers;
	private String[] selectedDoubleNumbers;
	private String[] selectedTripleNumbers;
	private String[] selectedQuadrupleNumbers;
	private String[] selectedSixfoldNumbers;
	private String[] selectedColumns;
	private String[] selectedDozens;
	private String[] selectedColours;
	private String[] selectedEvenOrOddNumbers;
	private String[] selectedHalfs;

	private int bet;

	public GameView() {
		super();
		numbers = new ArrayList<>();
	}

	@PostConstruct
	public void init() {
		for(int i=1; i<=36; ++i) {
			numbers.add(i);
		}
	}

	public void play() {
		System.out.println("selectedNumbers:");
		for (String s : selectedNumbers) {
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

	public String[] getSelectedNumbers() {
		return selectedNumbers;
	}

	public void setSelectedNumbers(String[] selectedNumbers) {
		this.selectedNumbers = selectedNumbers;
	}
	
}
