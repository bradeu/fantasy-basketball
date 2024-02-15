package model;

import org.junit.jupiter.api.*;

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
}
