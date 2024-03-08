package persistence;

import model.Game;
import model.Team;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

import org.json.*;

//Citation: https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo

// Represents a reader that reads game from JSON data stored in file
public class JsonReader {
    private String source;

    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads game from file and returns it;
    // throws IOException if an error occurs reading data from file
    public Game read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseGame(jsonObject);
    }

    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    //EFFECTS: parse the game based on the data in the json and returns it
    private Game parseGame(JSONObject jsonObject) {
        Game game = new Game();
        for (Object json1 : jsonObject.getJSONArray("teamList")) {
            JSONObject teamObject = (JSONObject) json1;
            Team team = new Team(teamObject.getString("name"));
            for (Object json2 : teamObject.getJSONArray("playerList")) {
                JSONObject playerObject = (JSONObject) json2;
                team.addPlayer(playerObject.getString("name"));
            }
            game.addTeam(team);
        }
        return game;
    }
}