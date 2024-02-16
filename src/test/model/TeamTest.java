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
        assertEquals(new ArrayList<>(), team.getPlayerList());
        assertEquals(0, team.getRating());
        assertTrue(team.getPlayerList().isEmpty());
    }

    @Test
    void testAddPlayer() {
        assertEquals(0, team.getRating());

        String playerName = "Stephen Curry";
        team.addPlayer(playerName);

        assertEquals(139.23, team.getRating());

        ArrayList<Player> players = team.getPlayerList();
        Player player = players.get(0);

        assertEquals(0+player.getSs(), team.getRating());

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

    @Test
    void testFindPlayerByNameExists() {
        Player player = team.findPlayerByName("data/players.csv","Lebron James");

        assertNotNull(player);

        assertEquals("Lebron James", player.getName());
        assertEquals(24.8, player.getPpg());
        assertEquals(7.8, player.getApg());
        assertEquals(7.2, player.getRpg());
        assertEquals(22.4, player.getPer());
        assertEquals(5, player.getWs());
        assertEquals(5.8, player.getBpm());
        assertEquals(149.91, player.getVorp());
        assertEquals("F", player.getPos());
        assertEquals(222.91, player.getSs());
    }

    @Test
    void testFindPlayerByNameNotExists() {
        Player player = team.findPlayerByName("data/players.csv","Ben");
        assertNull(player);
    }

    @Test
    void testFindPlayerByNameExistsCaseSensitive() {
        Player player = team.findPlayerByName("data/test.csv","joel embiid");

        assertNotNull(player);

        assertEquals("Joel Embiid", player.getName());
        assertEquals(35.3, player.getPpg());
        assertEquals(5.7, player.getApg());
        assertEquals(11.3, player.getRpg());
        assertEquals(34.3, player.getPer());
        assertEquals(6.8, player.getWs());
        assertEquals(11.83, player.getBpm());
        assertEquals(30.91, player.getVorp());
        assertEquals("C", player.getPos());
        assertEquals(136.14, player.getSs());
    }

    @Test
    void testFindPlayerByNameTestCSV() {
        Player player = team.findPlayerByName("data/test.csv","joel embiid");

        assertNotNull(player);

        assertEquals("Joel Embiid", player.getName());
        assertEquals(35.3, player.getPpg());
        assertEquals(5.7, player.getApg());
        assertEquals(11.3, player.getRpg());
        assertEquals(34.3, player.getPer());
        assertEquals(6.8, player.getWs());
        assertEquals(11.83, player.getBpm());
        assertEquals(30.91, player.getVorp());
        assertEquals("C", player.getPos());
        assertEquals(136.14, player.getSs());
    }

    @Test
    void testFindPlayerByNameTestCSV2() {
        Player player = team.findPlayerByName("data/test2.csv","joel embiid");
        assertNull(player);
    }

    @Test
    void testFindPlayerByNameTestCSV3() {
        Player player = team.findPlayerByName("data/test3.csv","joel embiid");
        assertNull(player);
    }

    @Test
    void testFindPlayerByNameTestCSV4() {
        Player player = team.findPlayerByName("data/test4.csv","joel embiid");
        assertNull(player);
    }

    @Test
    void testFindPlayerByNameNoCSV() {
        Player player = team.findPlayerByName("data/tests.csv","joel embiid");
        assertNull(player);
    }
}
