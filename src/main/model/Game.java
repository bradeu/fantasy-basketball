package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.ArrayList;

// Represents a basketball game with an arbitrary amount of teams
public class Game implements Writable {

    private ArrayList<Team> teamList;

    //EFFECTS: creates an instance of game with an empty array
    public Game() {
        this.teamList = new ArrayList<>();
    }

    //EFFECTS: returns whether teamList is empty
    public boolean isEmpty() {
        return teamList.isEmpty();
    }

    //MODIFIES: this
    //EFFECTS: adds team into the teamList
    public void addTeam(Team team) {
        teamList.add(team);
    }

    //EFFECTS: returns the array teamList
    public ArrayList<Team> getTeamList() {
        return teamList;
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("teamList", teamsToJson());
        return json;
    }

    // EFFECTS: returns players in this team as a JSON array
    private JSONArray teamsToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Team team : teamList) {
            jsonArray.put(team.toJson());
        }

        return jsonArray;
    }
}
