package model;

public class Player {
    private String name;
    private float ppg; // POINTS PER GAME
    private float apg; // ASSISTS PER GAME
    private float rpg; // REBOUNDS PER GAME
    private float per; // PLAYER EFFECIENCY RATING
    private float ws; // WIN SHARES
    private float bpm; // BOX PLUS MINUS
    private float vorp; // VALUE OVER REPLACEMENT
    private String pos; // POSITION
    private float ss; // SEASON SCORE

    public Player(String name, float ppg, float apg, float rpg, float per, float ws, float bpm, String pos) {
        this.name = name;
        this.ppg = ppg;
        this.apg = apg;
        this.rpg = rpg;
        this.per = per;
        this.ws = ws;
        this.bpm = bpm;
        this.pos = pos;
        this.ss = ppg + apg + rpg + per + ws + bpm;
    }

    public String getName() {
        return name;
    }

    public String getPos() {
        return pos;
    }

    public float getSs() {
        return ss;
    }
}
