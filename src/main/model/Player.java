package model;

public class Player {
    private String name;
    private double ppg; // POINTS PER GAME
    private double apg; // ASSISTS PER GAME
    private double rpg; // REBOUNDS PER GAME
    private double per; // PLAYER EFFECIENCY RATING
    private double ws; // WIN SHARES
    private double bpm; // BOX PLUS MINUS
    private double vorp; // VALUE OVER REPLACEMENT
    private String pos; // POSITION
    private double ss; // SEASON SCORE

    public Player(String name, double ppg, double apg, double rpg, double per, double ws, double bpm, String pos) {

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

    public double getSs() {
        return ss;
    }
}
