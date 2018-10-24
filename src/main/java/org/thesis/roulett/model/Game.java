package org.thesis.roulett.model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table
public class Game {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	private String playername;
	private String date;
	private int bet;
	private boolean isLost;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "player_fk")
	@JsonIgnore
	private Player player;

	public Game() {
		super();
	}
	
	public Game(String playername, String date, boolean isLost, int bet, Player player) {
		super();
		this.playername = playername;
		this.date = date;
		this.isLost = isLost;
		this.player = player;
		this.bet = bet;
	}
	
	public Game(Long id, String playername, String date, boolean isLost, int bet, Player player) {
		super();
		this.id = id;
		this.playername = playername;
		this.date = date;
		this.isLost = isLost;
		this.player = player;
		this.bet = bet;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getPlayername() {
		return playername;
	}

	public void setPlayername(String playername) {
		this.playername = playername;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public boolean getIsLost() {
		return isLost;
	}

	public void setLost(boolean isLost) {
		this.isLost = isLost;
	}

	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}

	public int getBet() {
		return bet;
	}

	public void setBet(int bet) {
		this.bet = bet;
	}

//	@Override
//	public String toString() {
//		return "Game [id=" + id + ", playername=" + playername + ", date=" + date + ", bet=" + bet + ", isLost="
//				+ isLost + ", player=" + player + "]";
//	}

}
