package org.thesis.roulett.dto;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

import javax.annotation.PostConstruct;

public class BallBean {

	private int number;
	private String colour;
	
	private List<Integer> reds;
	
	private Random rand;
	
	public BallBean() {
		super();
		
		rand = new Random();
		reds = Arrays.asList(1,3,5,7,912,14,16,18,19,21,23,25,27,30,32,34,36);
		
		spin();
	}
	
	public int getNumber() {
		return number;
	}
	
	public void setNumber(int number) {
		this.number = number;
	}
	
	public String getColour() {
		return colour;
	}
	
	public void setColour(String colour) {
		this.colour = colour;
	}
	
	public void spin() {
		number = rand.nextInt(37);
		
		if (number == 0) {
			colour = "Green";
		} else if (reds.contains(number)) {
			colour = "Red";
		} else {
			colour = "Black";
		}
	}
	
}
