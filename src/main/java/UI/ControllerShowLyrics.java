package UI;

import Artist.Music;
import Shared.UserRequest;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class ControllerShowLyrics implements Initializable {
    // Attributes
    Parent root;
    Stage stage;
    Scene scene;
    private Music music;
    @FXML
    private Label lyrics;

    public ControllerShowLyrics(Music music) {
        this.music = music;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        lyrics.setText("Music Title : " + music.getTitle() + " Artists : " + music.getArtists() + "\n");
        // add lyrics tu the lyrics label
    }
}
