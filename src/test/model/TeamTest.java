package model;

import org.junit.jupiter.api.*;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class TeamTest {

    private Team team;

    @BeforeEach
    void setup() {
        team = new Team("test");
    }

    @Test
    void testContructor() {
        assertEquals("test", team.getName());
        assertEquals(0, team.getRating());
    }

    @Test
    void testAddPlayer() {
        String playerName = "Stephen Curry";
        team.addPlayer(playerName);

        ArrayList<Player> players = team.getPlayerList();
        Player player = players.get(0);

        assertEquals("Stephen Curry", player.getName());
        assertEquals(28.0, player.getPpg());
        assertEquals(4.9, player.getApg());
        assertEquals(4.4, player.getRpg());
        assertEquals(21.7, player.getPer());
        assertEquals(5.3, player.getWs());
        assertEquals(6.1, player.getBpm());
        assertEquals(68.83, player.getVorp());
        assertEquals("G", player.getPos());
        assertEquals(139.23, player.getSs());
    }
}
