package UI;

import Artist.Music;
import User.User;
import Artist.PlayList;
import Shared.UserRequest;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.Slider;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;

public class ControllerPlaylistPlayer_19 implements Initializable {

    // Attributes
    Parent root;
    Stage stage;
    Scene scene;
    @FXML
    private Pane pane;
    @FXML
    private Label songName;
    @FXML
    private Button playButton, pauseButton, previousButton, nextButton, resetButton, lyricsButton;
    @FXML
    private Slider volumeController;
    @FXML
    private ProgressBar songProgressBar;
    @FXML
    private Button exit;
    @FXML
    private Button minimize;

    private File directory;
    private File[] files;

    private ArrayList<File> songs = new ArrayList<>();
    private int songID;
    private Timer timer;
    private TimerTask task;
    private boolean running;
    private Media media;
    private MediaPlayer mediaPlayer;
    private String arr[];
    private UserRequest userRequest;
    private User user;

    // Constructor
    public ControllerPlaylistPlayer_19(UserRequest userRequest, User user, String[] arr) {
        this.userRequest = userRequest;
        this.user = user;
        this.arr = arr;
    }

    // Public Functions
//    public ControllerPlaylistPlayer_19() throws IOException {
//        int len = this.arr.length;
//        String[] file_path = new String[len];
//        for (int i = 0; i < len; i++) {
//            file_path[i] = this.userRequest.musicAddress(arr[i]);
//        }
//    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Image exitImage;
        Image minimizeImage;
        try {
            exitImage = new Image(new FileInputStream("D:\\SBU\\Term 2\\AP\\Assignments\\Spotify\\src\\main\\resources\\UI\\exit2.png"));
            exit.setGraphic(new ImageView(exitImage));
            minimizeImage = new Image(new FileInputStream("D:\\SBU\\Term 2\\AP\\Assignments\\Spotify\\src\\main\\resources\\UI\\minimize2.png"));
            minimize.setGraphic(new ImageView(minimizeImage));
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        int len = this.arr.length;
//        String[] file_path = new String[len];
        for (int i = 0; i < len; i++) {
            try {
//                file_path[i] = this.userRequest.musicAddress(arr[i]);
                songs.add(new File(this.userRequest.musicAddress(arr[i])));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
//        if (files != null) {
//            for (File file : files) {
//                songs.add(file);
//
//            }
//        }
        media = new Media(songs.get(songID).toURI().toString());
        mediaPlayer = new MediaPlayer(media);

        songName.setText(songs.get(songID).getName());
        volumeController.valueProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                mediaPlayer.setVolume(volumeController.getValue() * 0.01);
            }
        });
        songProgressBar.setStyle("-fx-accent:  #1ed760;");
    }

    public void PlayButton() {
        BeginTimer();
        mediaPlayer.setVolume(volumeController.getValue() * 0.01);
        mediaPlayer.play();

    }

    public void PauseButton() {
        CancelTimer();
        mediaPlayer.pause();

    }

    public void ResetButton() {
        songProgressBar.setProgress(0);
        mediaPlayer.seek(Duration.seconds(0));
    }

    public void PreviousButton() {
        if (songID > 0) {
            songID--;
            mediaPlayer.stop();
            if(running){
                CancelTimer();
            }

            media = new Media(songs.get(songID).toURI().toString());
            mediaPlayer = new MediaPlayer(media);
            songName.setText(songs.get(songID).getName());
            PlayButton();

        } else {
            songID = songs.size() - 1;
            mediaPlayer.stop();
            if(running){
                CancelTimer();
            }

            media = new Media(songs.get(songID).toURI().toString());
            mediaPlayer = new MediaPlayer(media);
            songName.setText(songs.get(songID).getName());
            PlayButton();
        }

    }

    public void NextButton() {
        if (songID < songs.size() - 1) {
            songID++;
            mediaPlayer.stop();
            if(running){
                CancelTimer();
            }

            media = new Media(songs.get(songID).toURI().toString());
            mediaPlayer = new MediaPlayer(media);
            songName.setText(songs.get(songID).getName());
            PlayButton();

        } else {
            songID = 0;
            mediaPlayer.stop();
            if(running){
                CancelTimer();
            }

            media = new Media(songs.get(songID).toURI().toString());
            mediaPlayer = new MediaPlayer(media);
            songName.setText(songs.get(songID).getName());
            PlayButton();
        }
    }

    public void BeginTimer() {
        timer = new Timer();
        task = new TimerTask() {
            public void run(){
                running = true;
                double current = mediaPlayer.getCurrentTime().toSeconds();
                double end = media.getDuration().toSeconds();
                songProgressBar.setProgress(current/end);
                if(current/end == 1){
                    CancelTimer();
                }
            }
        };
        timer.scheduleAtFixedRate(task,0,1000);
    }

    public void CancelTimer() {
        running = false;
        timer.cancel();

    }
    public void showLyrics(ActionEvent event) throws IOException {
        Stage stage = new Stage();
        stage.setTitle("Spotify");
        stage.getIcons().add(new Image("D:\\SBU\\Term 2\\AP\\Assignments\\Spotify\\src\\main\\resources\\UI\\spotify-icon-marilyn-scott-0.png"));
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("showLyrics.fxml"));
        loader.setControllerFactory(type -> {
            if (type == ControllerShowLyrics.class) {
                //return new ControllerShowLyrics();
            }
            try {
                return type.getConstructor().newInstance();
            }
            catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
        Parent root = loader.load();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.showAndWait();
    }
    public void exitHandler(ActionEvent event) {
        stage = (Stage) (exit.getParent().getScene().getWindow());
        if (running) {
            PauseButton();
        }
        stage.close();
    }
    public void minimizeHandler(ActionEvent event) {
        stage = (Stage) (minimize.getParent().getScene().getWindow());
        stage.setIconified(true);
    }
}
