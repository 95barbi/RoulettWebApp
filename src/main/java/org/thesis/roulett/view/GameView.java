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
import org.thesis.roulett.service.GameService;
import org.thesis.roulett.service.PlayerService;

@Component
@ViewScoped
public class GameView extends BaseView {

//	@Autowired
//	private PlayerRepository playerRepository;
//
//	@Autowired
//	private GameRepository gameRepository;
	
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

	private String[] selectedNumbers;
	private String[] selectedDoubleNumbers;
	private String[] selectedTripleNumbers;
	private String[] selectedQuadrupleNumbers;
	private String[] selectedSixfoldNumbers;
	private String[] selectedColumns;
	private String[] selectedDozens;
	private String[] selectedColours;
	private String[] selectedParities;
	private String[] selectedHalfs;

	private int bet;

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
		parities = Arrays.asList("Evens", "Odds");
		halfs = Arrays.asList("1-18", "19-36");
	}

	@PostConstruct
	public void init() {
		for (int i = 1; i <= 36; ++i) {
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

	}

	public void play() {
		System.out.println("selectedNumbers:");
		
		for (String s : selectedNumbers) {
			System.out.println(s);
		}
		
		for (String s : selectedDoubleNumbers) {
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

}
