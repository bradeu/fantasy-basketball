package model;

import java.util.ArrayList;

// Represents a basketball game with an arbitrary amount of teams
public class Game {

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
}
