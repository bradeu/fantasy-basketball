package model;

import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

// Represents a team that contains an arbitrary amount of players
public class Team {

    private ArrayList<Player> playerList;
    private String name;
    private double rating;

    private String filePath = "data/players.csv";

    //EFFECTS: creates a team with name and an empty arraylist and 0 rating
    public Team(String name) {
        this.playerList = new ArrayList<>();
        this.name = name;
        this.rating = 0;
    }

    //EFFECTS: returns the name of the team
    public String getName() {
        return name;
    }

    //EFFECTS: returns the cumulative rating of team
    public double getRating() {
        return rating;
    }

    //REQUIRES: playerName should only be from players.csv
    //MODIFIES: this
    //EFFECTS: add player with playerName into the list and adds the rating of
    //         the team by the player's season score
    public void addPlayer(String playerName) {
        Player player = findPlayerByName(filePath, playerName);
        playerList.add(player);
        rating += player.getSs();
    }

    //EFFECTS: return the list of player in the team
    public ArrayList<Player> getPlayerList() {
        return playerList;
    }

    //REQUIRES: searchName should only be from players.csv
    //EFFECTS: search for the player from filePath with searchName and return the player
    public static Player findPlayerByName(String filePath, String searchName) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length >= 9 && parts[0].equalsIgnoreCase(searchName)) {
                    String name = parts[0];
                    double ppg = Double.parseDouble(parts[1]);
                    double apg = Double.parseDouble(parts[2]);
                    double rpg = Double.parseDouble(parts[3]);
                    double per = Double.parseDouble(parts[4]);
                    double ws = Double.parseDouble(parts[5]);
                    double bpm = Double.parseDouble(parts[6]);
                    double vorp = Double.parseDouble(parts[7]);
                    String pos = parts[8];
                    return new Player(name, ppg, apg, rpg, per, ws, bpm, vorp, pos);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
