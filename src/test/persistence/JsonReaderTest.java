package persistence;

import model.Game;
import model.Team;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class JsonReaderTest extends JsonTest {

    @Test
    void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/NonExistentGame.json");
        try {
            Game game = reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testReaderEmptyGame() {
        JsonReader reader = new JsonReader("./data/testReaderEmptyGame.json");
        try {
            Game game = reader.read();
            assertTrue(game.isEmpty());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderGeneralGame() {
        JsonReader reader = new JsonReader("./data/testReaderGeneralGame.json");
        try {
            Game game = reader.read();
            List<Team> teamList = game.getTeamList();
            assertEquals(2, teamList.size());
            ArrayList<String> playerNameList1 = new ArrayList<>();
            ArrayList<String> playerNameList2 = new ArrayList<>();
            playerNameList1.add("Luka Doncic");
            playerNameList2.add("Lebron James");
            checkTeam("a", playerNameList1, teamList.get(0));
            checkTeam("b", playerNameList2, teamList.get(1));
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }
}