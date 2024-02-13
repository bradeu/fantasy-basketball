package model;

import java.util.ArrayList;

public class Team {

    private ArrayList<Player> playerList;
    private String name;
    private float rating;

    public Team(String name) {
        this.playerList = new ArrayList<Player>();
        this.name = name;
        this.rating = 0;
    }

    public String getName() {
        return name;
    }

    public float getRating() {
        return rating;
    }

    public void addPlayer(Player player) {
        playerList.add(player);
        rating += player.getSs();
    }

    public int getSize() {
        return playerList.size();
    }
}
