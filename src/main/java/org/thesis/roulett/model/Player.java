package org.thesis.roulett.model;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table
public class Player {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	private String name;
	private String username;
	private String email;
	private String password;
	private int balance;
	private String role;
	private boolean isBroken;

	@OneToMany(mappedBy = "player", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<Game> games;
	
	public Player() {
		super();
	}
	
	public Player(String name, String username, String email, String password, int balance, String role) {
		super();
		this.name = name;
		this.username = username;
		this.email = email;
		this.password = password;
		this.balance = balance;
		this.role = role;
		this.games = new ArrayList<>();
		countIsBroken();
	}

	public Player(Long id, String name, String username, String email, String password, int balance, String role) {
		super();
		this.id = id;
		this.name = name;
		this.username = username;
		this.email = email;
		this.password = password;
		this.balance = balance;
		this.role = role;
		this.games = new ArrayList<>();
	}
	
	@PostConstruct
	private void countIsBroken() {
		isBroken = true;
		
		if (balance > 0) {
			isBroken = false;
		}
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getBalance() {
		return balance;
	}

	public void setBalance(int balance) {
		this.balance = balance;
	}

	public boolean getIsBroken() {
		return isBroken;
	}

	public void setBroken(boolean isBroken) {
		this.isBroken = isBroken;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public List<Game> getGames() {
		return games;
	}

	public void setGames(List<Game> games) {
		this.games = games;
	}
	
	public void addGame(Game game) {
		games.add(game);
	}

//	@Override
//	public String toString() {
//		return "Player [id=" + id + ", name=" + name + ", username=" + username + ", email=" + email + ", password="
//				+ password + ", balance=" + balance + ", role=" + role + ", isBroken=" + isBroken + ", games=" + games
//				+ "]";
//	}

}
