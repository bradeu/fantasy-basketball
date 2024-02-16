package model;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

public class PlayerTest {

    private Player player;

    @BeforeEach
    void setup() {
        player = new Player("test", 0, 0, 0, 0, 0, 0, 0, "G");
    }

    @Test
    void testConstructor() {
        assertEquals("test", player.getName());
        assertEquals(0, player.getPpg());
        assertEquals(0, player.getApg());
        assertEquals(0, player.getRpg());
        assertEquals(0, player.getPer());
        assertEquals(0, player.getWs());
        assertEquals(0, player.getBpm());
        assertEquals(0, player.getVorp());
        assertEquals("G", player.getPos());
        assertEquals(0, player.getSs());
    }
}
