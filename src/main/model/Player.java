package model;

// Represents a player in the basketball game
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

    //EFFECTS: creates a player with name, ppg, apg, rpg, per, ws, bpm, vorp, and pos
    public Player(String name, double ppg, double apg, double rpg, double per, double ws, double bpm,
                  double vorp, String pos) {
        this.name = name;
        this.ppg = ppg;
        this.apg = apg;
        this.rpg = rpg;
        this.per = per;
        this.ws = ws;
        this.bpm = bpm;
        this.pos = pos;
        this.vorp = vorp;
        this.ss = ppg + apg + rpg + per + ws + bpm + vorp;
    }

    //EFFECTS: return the name of the player
    public String getName() {
        return name;
    }

    //EFFECTS: return the points per game of the player
    public double getPpg() {
        return ppg;
    }

    //EFFECTS: return the assists per game of the player
    public double getApg() {
        return apg;
    }

    //EFFECTS: return the rebounds per game of the player
    public double getRpg() {
        return rpg;
    }

    //EFFECTS: return the player efficiency rating of the player
    public double getPer() {
        return per;
    }

    //EFFECTS: return the win share of the player
    public double getWs() {
        return ws;
    }

    //EFFECTS: return the box-plus minus of the player
    public double getBpm() {
        return bpm;
    }

    //EFFECTS: return the value over replacement of the player
    public double getVorp() {
        return vorp;
    }

    //EFFECTS: return the position of the player
    public String getPos() {
        return pos;
    }

    //EFFECTS: return the season score of the player
    public double getSs() {
        return ss;
    }
}
