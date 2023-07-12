package Shared;

import Artist.Artist;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONObject;

import java.io.IOException;
import java.sql.*;

public class ArtistResponse {

    // Attributes

    Connection connection;
    Statement statement;

    // Constructor

    public ArtistResponse() throws SQLException {
        this.connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/spotify",
                "root",
                "password");
        this.statement = connection.createStatement();
    }

    // Public Functions
    public String checkUsernameExist(String name) throws SQLException {
        String sqlCommand = "SELECT count(*) FROM artist WHERE name = " + '"' + name + '"';
        ResultSet checkNameResult = statement.executeQuery(sqlCommand);
        checkNameResult.next();
        int numberCheck = Integer.parseInt(checkNameResult.getString("count(*)"));
        boolean answer = numberCheck != 0;
        return String.valueOf(answer);
    }

    public String checkPasswordForLoginOperation(String name, String password) throws SQLException, IOException {
        Artist artist = null;
        String sqlCommand = "SELECT id, password, email_address, biography FROM artist WHERE name = " + '"' + name + '"';
        ResultSet checkForArtistResult = statement.executeQuery(sqlCommand);
        checkForArtistResult.next();
        String dbPassword = checkForArtistResult.getString("password");
        if (dbPassword.equals(password)) {
            String id = checkForArtistResult.getString("id");
            String email_address = checkForArtistResult.getString("email_address");
            String biography = checkForArtistResult.getString("biography");
            artist = new Artist(id, name, dbPassword, email_address, biography);
        }
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonData = objectMapper.writeValueAsString(artist);
        return jsonData;
    }

    public String addArtistToDB(JSONObject jsonObject) throws SQLException {
        String sqlCommand = "INSERT INTO artist VALUES (\"" + jsonObject.getString("iD") + "\",\"" +
                jsonObject.getString("name") + "\",\"" + jsonObject.getString("password") + "\",\"" +
                jsonObject.getString("emailAddress") + "\",\"" + jsonObject.getString("biography") + "\")";
        int addArtist = statement.executeUpdate(sqlCommand);
        return "";
    }
}
