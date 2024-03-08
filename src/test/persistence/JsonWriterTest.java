package persistence;

import model.Game;
import model.Team;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

//Citation: https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo

class JsonWriterTest extends JsonTest {

    @Test
    void testWriterInvalidFile() {
        try {
            Game game = new Game();
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testWriterEmptyWorkroom() {
        try {
            Game game = new Game();
            JsonWriter writer = new JsonWriter("./data/testWriterEmptyGame.json");
            writer.open();
            writer.write(game);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterEmptyGame.json");
            game = reader.read();
            assertTrue(game.isEmpty());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterGeneralWorkroom() {
        try {
            Game game = new Game();
            Team a = new Team("a");
            Team b = new Team("b");
            a.addPlayer("luka doncic");
            b.addPlayer("lebron james");
            game.addTeam(a);
            game.addTeam(b);
            JsonWriter writer = new JsonWriter("./data/testWriterGeneralGame.json");
            writer.open();
            writer.write(game);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterGeneralGame.json");
            game = reader.read();
            List<Team> teamList = game.getTeamList();
            assertEquals(2, teamList.size());
            ArrayList<String> playerNameList1 = new ArrayList<>();
            ArrayList<String> playerNameList2 = new ArrayList<>();
            playerNameList1.add("Luka Doncic");
            playerNameList2.add("Lebron James");
            checkTeam("a", playerNameList1, teamList.get(0));
            checkTeam("b", playerNameList2, teamList.get(1));

        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }
}
