package ui;

import model.Game;
import model.Team;

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
        game.playGame();
    }
}
