package model;

import java.util.ArrayList;
import java.util.Random;

public class Game {

    private ArrayList<Team> teamList;

    public Game() {
        this.teamList = new ArrayList<>();
    }

    public void addTeam(Team team) {
        teamList.add(team);
    }

    public void simGame(Team winTeam, Team loseTeam) {
        Random random = new Random();
        int scoreOne = 0;
        int scoreTwo = 0;
        while ((scoreOne < 130 && scoreTwo < 130) || scoreOne == scoreTwo) {
            int randomScoreOne = random.nextInt(10);
            int randomScoreTwo = random.nextInt(10);
            scoreOne += randomScoreOne;
            scoreTwo += randomScoreTwo;
            System.out.println(scoreOne + "-" + scoreTwo);
        }
        System.out.println(winTeam.getName() + " wins the match against " + loseTeam.getName() + "!");
    }

    public void playGame() {
        if (teamList.size() < 2) {
            System.out.println("Not enough teams to play a game");
            return;
        }

        Team winner = teamList.get(0);
        for (int i = 1; i < teamList.size(); i++) {
            Team team = teamList.get(i);
            if (team.getRating() > winner.getRating()) {
                simGame(team, winner);
                winner = team;
            } else {
                simGame(winner, team);
            }
        }
        System.out.println(winner.getName() + " wins the game !");
    }
}
