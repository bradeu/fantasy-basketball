package model;

import org.junit.jupiter.api.*;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class GameTest {

    private Game game;

    @BeforeEach
    void setup() {
        game = new Game();
    }

    @Test
    void testContructor() {
        assertTrue(game.isEmpty());
    }

    @Test
    void testAddTeam() {
        Team team = new Team("test");

        game.addTeam(team);
        ArrayList<Team> teams = game.getTeamList();
        assertTrue(teams.contains(team));
    }

}
