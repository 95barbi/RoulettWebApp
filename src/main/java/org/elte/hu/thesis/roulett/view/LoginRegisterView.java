package org.elte.hu.thesis.roulett.view;

import javax.faces.view.ViewScoped;

import org.elte.hu.thesis.roulett.model.Player;
import org.elte.hu.thesis.roulett.repository.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@ViewScoped
public class LoginRegisterView extends BaseView {

	private String username;
	private String password;
	private String email;
	private String name;
	private int balance;
	
	@Autowired
	private PlayerRepository repository;
	
	public LoginRegisterView() {
		super();
	}

	public LoginRegisterView(String username, String password, String email, String name, int balance,
			PlayerRepository repository) {
		super();
		this.username = username;
		this.password = password;
		this.email = email;
		this.name = name;
		this.balance = balance;
		this.repository = repository;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getBalance() {
		return balance;
	}

	public void setBalance(int balance) {
		this.balance = balance;
	}

	public PlayerRepository getRepositor() {
		return repository;
	}

	public void setRepositor(PlayerRepository repository) {
		this.repository = repository;
	}
	
	public String login() {
		System.out.println("LoginRegisterVire.login()");
		Player player = null;
		player = repository.findPlayerIfRegistered(username, password);
		
		if (player != null) {
			System.out.println("Succesfully registered!");
			
			if (player.getRole().equals("ADMIN")) {
				return this.redirect("adminHomePage");
			} else {
				return this.redirect("userHomePage");
			}
		} else {
			System.out.println("Incorrect username or password!");
		}
		
		return null;
	}
	
	public String register() {
		System.out.println("LoginRegisterVire.registration()");
		
		Player player = new Player(name, username, email, password, balance, "USER");
		repository.save(player);
		
		return this.redirect("userHomePage");
	}
	
	public String toIndex() {
		return this.redirect("index");
	}
	
}
