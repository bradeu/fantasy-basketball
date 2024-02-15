package model;

import java.util.ArrayList;
import java.util.Random;

public class Game {

    private ArrayList<Team> teamList;

    public Game() {
        this.teamList = new ArrayList<Team>();
    }

    public void addTeam(Team team) {
        teamList.add(team);
    }

    public void simGame(Team winTeam) {
        Random random = new Random();
        int winScore = 0;
        int loseScore = 0;
        while (winScore < 140) {
            int randomScoreOne = random.nextInt(10);
            int randomScoreTwo = randomScoreOne + 1 + random.nextInt(1);
            winScore += randomScoreTwo;
            loseScore += randomScoreOne;
            System.out.println(winScore + "-" + loseScore);
        }
        System.out.println(winTeam.getName() + " wins the match !");
    }

    public String playGame() {
        if (teamList.size() <= 2) {
            return "Not enough teams to play a game.";
        }

        Team winner = teamList.get(0);
        for (int i = 1; i < teamList.size(); i++) {
            Team team = teamList.get(i);
            if (team.getRating() > winner.getRating()) {
                simGame(team);
                winner = team;
            } else {
                simGame(winner);
            }
        }
        return winner.getName() + " wins the game !";
    }
}
