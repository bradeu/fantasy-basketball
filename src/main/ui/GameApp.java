package ui;

import model.Game;
import model.Team;
import persistence.JsonReader;
import persistence.JsonWriter;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

// Represents a running basketball game
public class GameApp {
    private static final String JSON_STORE = "./data/game.json";
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;

    //EFFECTS: runs the basketball game
    public GameApp() throws FileNotFoundException {
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
        runApp();
    }

    //EFFECTS: runs the game based on the user input whether to show the teams or play the game or quit application
    public void runApp() {
        Scanner scanner = new Scanner(System.in);
        int playerAmt;
        Game game = new Game();
        int response;
        do {
            System.out.println("Please enter a number:\n(1)Play game\n(2)Show team\n(3)Add team\n(4)Load previous game\n(5)Save game\n(6)Quit application");
            response = scanner.nextInt();
            switch (response) {
                case 1:
                    playGame(game);
                    break;
                case 2:
                    game.getTeamList().forEach(team -> System.out.println(team.getName()));
                    break;
                case 3:
                    System.out.println("Enter number of players for this team:");
                    playerAmt = scanner.nextInt();
                    game = addTeam(game, playerAmt);
                    break;
                case 4:
                    game = loadGame();
                    break;
                case 5:
                    saveGame(game);
                    break;
                case 6:
                    System.out.println("Thank you for playing !");
                    break;
                default:
                    System.out.println("Invalid response please try again !");
            }
        } while (response != 6);
    }


    //EFFECTS: prints out a simulation of a game with scores and match winner declaration
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

    //REQUIRES: teamList.size() < 2
    //EFFECTS: looks into the teamList in game and checks which team has the highest rating and runs every game through
    //         simGame, also prints out the winner of the overall game
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

    // EFFECTS: adds a team with the playerAmt into the game
    public Game addTeam(Game game, int playerAmt) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter team name:");
        String teamName = scanner.nextLine();

        Team team = new Team(teamName);

        for (int i = 0; i < playerAmt; i++) {
            System.out.println("Enter player name:");
            String playerName = scanner.nextLine();
            team.addPlayer(playerName);
        }
        game.addTeam(team);
        return game;
    }

    // EFFECTS: saves the game to file
    private void saveGame(Game game) {
        try {
            jsonWriter.open();
            jsonWriter.write(game);
            jsonWriter.close();
            System.out.println("Saved to " + JSON_STORE);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STORE);
        }
    }

    // MODIFIES: this
    // EFFECTS: loads game from file
    private Game loadGame() {
        Game game = new Game();
        try {
            game = jsonReader.read();
            System.out.println("Game loaded from " + JSON_STORE);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE);
        }
        return game;
    }
}
