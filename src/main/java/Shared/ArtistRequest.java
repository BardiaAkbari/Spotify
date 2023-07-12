package Shared;

import Artist.Artist;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONObject;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class ArtistRequest {

    // Attributes
    private Scanner input;
    private PrintWriter output;

    // Constructor
    public ArtistRequest(Scanner input, PrintWriter output) {
        this.output = output;
        this.input = input;
    }

    // Public Functions
    public boolean nameCheckingRequest(String name) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("number", "1");
        jsonObject.put("name", name);
        String jsonCommand = jsonObject.toString();
        output.println(jsonCommand);
        output.flush();
        String answer = input.nextLine();
        return Boolean.parseBoolean(answer);
    }

    public Artist checkPasswordForLoginOperation (String name, String password) throws IOException {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("number", "2");
        jsonObject.put("name", name);
        jsonObject.put("password", password);
        String jsonCommand = jsonObject.toString();
        output.println(jsonCommand);
        output.flush();
        String answer = input.nextLine();
        return new ObjectMapper().readValue(answer, Artist.class);
    }

    public String addArtistToDB(Artist artist) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonData = objectMapper.writeValueAsString(artist);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("number", "3");
        jsonObject.put("artist", new JSONObject(jsonData));
        String jsonCommand = jsonObject.toString();
        output.println(jsonCommand);
        output.flush();
        return input.nextLine();
    }
}
