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
            winScore += random.nextInt(10);
            loseScore += random.nextInt(10);
            System.out.println(winScore - loseScore);
        }
        System.out.println(winTeam.getName() + "wins the match !");
    }

    public String playGame() {
        Team winner = new Team("no winner");
        for (Team team : teamList) {
            if (team.getRating() > winner.getRating()) {
                simGame(team);
                winner = team;
            }
        }
        return winner.getName() + "wins the game !";
    }
}
