package ui;

import model.Game;
import model.Team;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class GameApp {

    public GameApp() {
        runGame();
    }

    public void runGame() {
        Scanner scanner = new Scanner(System.in);

        Game game = new Game();

        System.out.println("Enter number of teams (minimum of 2):");
        int teamAmt = scanner.nextInt();
        scanner.nextLine();

        System.out.println("Enter number of players per team:");
        int playerAmt = scanner.nextInt();
        scanner.nextLine();

        for (int index = 0; index < teamAmt; index++) {
            System.out.println("Enter team name:");
            String teamName = scanner.nextLine();

            Team team = new Team(teamName);

            for (int i = 0; i < playerAmt; i++) {
                System.out.println("Enter player name:");
                String playerName = scanner.nextLine();
                team.addPlayer(playerName);
            }
            game.addTeam(team);
        }
        playGame(game);
    }

    public void simGame(Team winTeam, Team loseTeam) {
        Random random = new Random();
        int scoreOne = 0;
        int scoreTwo = 0;
        while ((scoreOne < 120 && scoreTwo < 120) || scoreOne == scoreTwo) {
            int randomScoreOne = random.nextInt(10);
            int randomScoreTwo = random.nextInt(10);
            scoreOne += randomScoreOne;
            scoreTwo += randomScoreTwo;
            System.out.println(scoreOne + "-" + scoreTwo);
        }
        System.out.println(winTeam.getName() + " wins the match against " + loseTeam.getName() + "!");
    }

    public void playGame(Game game) {

        ArrayList<Team> teamList = game.getTeamList();

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
