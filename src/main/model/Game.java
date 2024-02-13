package model;

import java.util.ArrayList;

public class Game {

    private ArrayList<Team> teamList;

    public Game() {
        this.teamList = new ArrayList<Team>();
    }

    public void addTeam(Team team) {
        teamList.add(team);
    }

    public String playGame() {
        Team winner = new Team("no name");
        for (Team team : teamList) {
            if (team.getRating() > winner.getRating()) {
                winner = team;
            }
        }
        return winner.getName();
    }
}
