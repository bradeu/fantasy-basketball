package model;

import java.util.ArrayList;
import java.util.Random;

public class Game {

    private ArrayList<Team> teamList;

    public Game() {
        this.teamList = new ArrayList<>();
    }

    public boolean isEmpty() {
        return teamList.isEmpty();
    }

    public void addTeam(Team team) {
        teamList.add(team);
    }

    public ArrayList<Team> getTeamList() {
        return teamList;
    }
}
