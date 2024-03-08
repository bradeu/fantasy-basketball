package persistence;

import model.Player;
import model.Team;
import model.Game;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class JsonTest {
    protected void checkTeam(String teamName, ArrayList<String> playerNameList, Team team) {
        assertEquals(teamName, team.getName());
        int index = 0;
        for (Player player : team.getPlayerList()) {
            assertEquals(player.getName(), playerNameList.get(index));
        }
    }
}
