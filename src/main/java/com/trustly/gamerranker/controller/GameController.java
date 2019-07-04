package com.trustly.gamerranker.controller;

import com.trustly.gamerranker.data.Game;
import com.trustly.gamerranker.repository.GameRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class GameController {

	private final GameRepo gameRepo;

	@Autowired
	public GameController(GameRepo gameRepo) {
		this.gameRepo = gameRepo;
	}

	@PostMapping("game")
	public void addNewGame(@RequestParam("gamename") String gameName) {
		gameRepo.saveGame(gameName);
	}

	@GetMapping("game")
	public List<Game> getAllGames() {
		return gameRepo.getAllgames();
	}
}
