package model;

import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import org.json.*;
import persistence.Writable;

// Represents a team that contains an arbitrary amount of players
public class Team implements Writable {

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
        EventLog.getInstance().logEvent(new Event(playerName + " added to the team"));
    }

    //EFFECTS: return the list of player in the team
    public ArrayList<Player> getPlayerList() {
        EventLog.getInstance().logEvent(new Event("Player list requested"));
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
                    EventLog.getInstance().logEvent(new Event(searchName + " found"));
                    return new Player(name, ppg, apg, rpg, per, ws, bpm, vorp, pos);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    //Citation: https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", name);
        json.put("playerList", playersToJson());
        return json;
    }

    // EFFECTS: returns players in this team as a JSON array
    private JSONArray playersToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Player p : playerList) {
            jsonArray.put(p.toJson());
        }

        return jsonArray;
    }
}
