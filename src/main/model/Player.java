package model;

public class Player {
    private String name;
    private int ppg; // POINTS PER GAME
    private int apg; // ASSISTS PER GAME
    private int rpg; // REBOUNDS PER GAME
    private int per; // PLAYER EFFECIENCY RATING
    private int ws; // WIN SHARES
    private int bpm; // BOX PLUS MINUS
    private int vorp; // VALUE OVER REPLACEMENT
    private String pos; // POSITION
    private int ss; // SEASON SCORE

    public Player(String name, int ppg, int apg, int rpg, int per, int ws, int bpm, String pos) {
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

    public int getSs() {
        return ss;
    }
}
