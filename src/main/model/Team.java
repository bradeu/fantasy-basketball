package model;

import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Team {

    private ArrayList<Player> playerList;
    private String name;
    private double rating;

    private String fileName = "data/players.csv";

    public Team(String name) {
        this.playerList = new ArrayList<Player>();
        this.name = name;
        this.rating = 0;
    }

    public String getName() {
        return name;
    }

    public double getRating() {
        return rating;
    }

    public void addPlayer(String playerName) {
        Player player = findPlayerByName(fileName, playerName);
        playerList.add(player);
        rating += player.getSs();
    }

    public static Player findPlayerByName(String filename, String searchName) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length >= 8 && parts[0].equalsIgnoreCase(searchName)) {
                    String name = parts[0];
                    double ppg = Double.parseDouble(parts[1]);
                    double apg = Double.parseDouble(parts[2]);
                    double rpg = Double.parseDouble(parts[3]);
                    double per = Double.parseDouble(parts[4]);
                    double ws = Double.parseDouble(parts[5]);
                    double bpm = Double.parseDouble(parts[6]);
                    String pos = parts[7];
                    return new Player(name, ppg, apg, rpg, per, ws, bpm, pos);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

}
