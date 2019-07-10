package com.trustly.gamerranker.controller;

import com.trustly.gamerranker.data.Match;
import com.trustly.gamerranker.repository.MatchRepo;
import com.trustly.gamerranker.repository.UserRepo;
import com.trustly.gamerranker.service.PointsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MatchController {

    private final UserRepo userRepo;
    private final MatchRepo matchRepo;
    private final PointsService pointsService;

    @Autowired
    public MatchController(UserRepo userRepo, MatchRepo matchRepo, PointsService pointsService) {
        this.userRepo = userRepo;
        this.matchRepo = matchRepo;
        this.pointsService = pointsService;
    }

    @PostMapping("match")
    public void addMatch(@RequestParam("gameid") Long gameId,
                         @RequestParam("user1name") String user1Name,
                         @RequestParam("user2name") String user2Name,
                         @RequestParam("scoreuser1") Integer scoreUser1,
                         @RequestParam("scoreuser2") Integer scoreUser2) {
        Match match = new Match();
        match.setGameId(gameId);
        Long user1Id = userRepo.getUserIdFromName(user1Name);
        Long user2Id = userRepo.getUserIdFromName(user2Name);
        match.setUser1Id(user1Id);
        match.setUser2Id(user2Id);
        match.setScoreUser1(scoreUser1);
        match.setScoreUser2(scoreUser2);
        matchRepo.saveMatch(match);

        if (scoreUser1 > scoreUser2) {
            pointsService.updatePoints(gameId, user1Id, user2Id);
        } else {
            pointsService.updatePoints(gameId, user2Id, user1Id);
        }
    }
}
