package org.thesis.roulett.view;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.thesis.roulett.model.Player;
import org.thesis.roulett.repository.PlayerRepository;
import org.thesis.roulett.service.PlayerService;

@Component
@ViewScoped
public class DepositView extends BaseView {

//	@Autowired
//	private PlayerRepository repository;
	
	@Autowired
	private PlayerService service;
	
	private Player player;
	
	private int depoMoney;
	private int widroMoney;

	public DepositView() {
		super();
	}
	
	@PostConstruct
	public void init() {
		player = LoginRegisterView.getPlayer();
	}

	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}
	
	public int getDepoMoney() {
		return depoMoney;
	}

	public void setDepoMoney(int depoMoney) {
		this.depoMoney = depoMoney;
	}
	
	public int getWidroMoney() {
		return widroMoney;
	}

	public void setWidroMoney(int widroMoney) {
		this.widroMoney = widroMoney;
	}

	public String addMoney() {
		player.setBalance(player.getBalance() + depoMoney);
		
//		repository.save(player);
		service.updatePlayer(player);
		
		return this.redirect("userHomePage");
	}
	
	public String withdrawMoney() {
		player.setBalance(player.getBalance() - widroMoney);
		
//		repository.save(player);
		service.updatePlayer(player);
		
		return this.redirect("userHomePage");
	}

}
