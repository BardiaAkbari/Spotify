package UI;

import Artist.Music;
import Shared.UserRequest;
import User.User;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.WindowEvent;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ControllerSearchMusic_7 implements Initializable  {

    // Attributes
    Parent root;
    Stage stage;
    Scene scene;
    private User user;
    private UserRequest userRequest;
    @FXML
    private TableView<Music> tableView;
    @FXML
    private TableColumn<Music, String> titleColumn;
    @FXML
    private TableColumn<Music, String> genreColumn;
    @FXML
    private TableColumn<Music, String> albumColumn;
    @FXML
    private TableColumn<Music, String> artistsColumn;
    @FXML
    private TableColumn<Music, String> durationColumn;
    @FXML
    private TableColumn<Music, String> trackIDColumn;
    @FXML
    private TextField search;
    @FXML
    private Label warning;
    ObservableList<Music> musicObservableList = FXCollections.observableArrayList();

    // Constructor

    public ControllerSearchMusic_7(User user, UserRequest userRequest) {
        this.user = user;
        this.userRequest = userRequest;
    }

    // Public Functions
    @Override
    public void initialize(URL location, ResourceBundle resources) {

        titleColumn.setCellValueFactory(new PropertyValueFactory<>("title"));
        genreColumn.setCellValueFactory(new PropertyValueFactory<>("genre"));
        albumColumn.setCellValueFactory(new PropertyValueFactory<>("album"));
        artistsColumn.setCellValueFactory(new PropertyValueFactory<>("artists"));
        durationColumn.setCellValueFactory(new PropertyValueFactory<>("duration"));
        trackIDColumn.setCellValueFactory(new PropertyValueFactory<>("trackID"));
        int allMusicsNumber = 0;
        try {
            allMusicsNumber = this.userRequest.numberOfAllMusics();
        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }
        for (int i = 1; i <= allMusicsNumber; i++) {
            JSONObject jsonObject = null;
            try {
                jsonObject = this.userRequest.getRow_iMusic(i);
            }
            catch (IOException e) {
                throw new RuntimeException(e);
            }
            String trackID = jsonObject.getString("track_id");
            String title = jsonObject.getString("title");
            String genre = jsonObject.getString("genre");
            String album = jsonObject.getString("album");
            String artists = jsonObject.getString("artist");
            String duration = jsonObject.getString("duration");
            musicObservableList.add(new Music(trackID, title, genre, album, artists, duration));
        }
        tableView.setItems(musicObservableList);

        FilteredList<Music> filteredList = new FilteredList<>(musicObservableList, b-> true);

        search.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredList.setPredicate(music -> {

                if (newValue.isEmpty() || newValue.isBlank() || newValue == null) {
                    return  true;
                }
                String searchKeyword = newValue.toLowerCase();
                if (music.getTitle().toLowerCase().indexOf(searchKeyword ) > -1){
                    return true;
                }
                else if (music.getGenre().toLowerCase().indexOf(searchKeyword) > -1) {
                    return true;
                }
                else if (music.getAlbum().toLowerCase().indexOf(searchKeyword) > -1) {
                    return true;
                }
                else if (music.getArtists().toLowerCase().indexOf(searchKeyword) > -1) {
                    return true;
                }
                else {
                        return false;
                }
            });
        });
        SortedList<Music> sortedList = new SortedList<>(filteredList);
        sortedList.comparatorProperty().bind(tableView.comparatorProperty());
        tableView.setItems(sortedList);
    }

    public void playButton(ActionEvent event) throws IOException {
        try {
            ObservableList<Music> musics = tableView.getSelectionModel().getSelectedItems();
            Music music = musics.get(0);
            warning.setText("");
            Stage stage = new Stage();
            stage.setTitle("Spotify");
            stage.getIcons().add(new Image("D:\\SBU\\Term 2\\AP\\Assignments\\Spotify\\src\\main\\resources\\UI\\spotify-icon-marilyn-scott-0.png"));
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("musicPlayer.fxml"));
            loader.setControllerFactory(type -> {
                if (type == ControllerMusicPlayer_18.class) {
                    return new ControllerMusicPlayer_18(this.userRequest, music);
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
            stage.initStyle(StageStyle.UNDECORATED);
            stage.showAndWait();
        }
        catch (IndexOutOfBoundsException e) {
            warning.setText("You did not choose any music to play!");
        }
        // music player plays the chosen music
    }
    public void downloadButton(ActionEvent event) throws IOException {
        try {
            ObservableList<Music> musics = tableView.getSelectionModel().getSelectedItems();
            Music music = musics.get(0);
            warning.setText("");
            if (!this.userRequest.checkIfUserDownloadsSpecificMusic(this.user.getUser_id(), music.getTrackID())) {
                this.userRequest.addMusicToFolder(music.getTrackID(), this.user.getUsername());
                this.userRequest.addDownload(this.user.getUser_id(), music.getTrackID());
                warning.setText(music.getTitle() + " is downloaded now!");
            }
            else {
                warning.setText("You have already downloaded " + music.getTitle());
            }
        }
        catch (IndexOutOfBoundsException e) {
            warning.setText("You did not choose any music to download!");
        }
    }
    public void likeButton(ActionEvent event) throws IOException {
        try {
            ObservableList<Music> musics = tableView.getSelectionModel().getSelectedItems();
            Music music = musics.get(0);
            warning.setText("");
            if (!this.userRequest.checkUserLike(this.user.getUser_id(), music.getTrackID())) {
                this.userRequest.addLike(this.user.getUser_id(), music.getTrackID());
                warning.setText(music.getTitle() + " is liked now!");
            }
            else {
                warning.setText("You have already liked " + music.getTitle());
            }
        }
        catch (IndexOutOfBoundsException e) {
            warning.setText("You did not choose any music to like!!");
        }
    }
    public void dislikeButton(ActionEvent event) throws IOException {
        try {
            ObservableList<Music> musics = tableView.getSelectionModel().getSelectedItems();
            Music music = musics.get(0);
            warning.setText("");
            if (this.userRequest.checkUserLike(this.user.getUser_id(), music.getTrackID())) {
                this.userRequest.addDisLike(this.user.getUser_id(), music.getTrackID());
                warning.setText(music.getTitle() + " is disliked now!");
            }
            else {
                warning.setText("You can not dislike " + music.getTitle());
            }
        }
        catch (IndexOutOfBoundsException e) {
            warning.setText("You did not choose any music to dislike!");
        }

    }

    public void switchToUserSearch(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("userSearch.fxml"));
        root = loader.load();
        ControllerUserSearch_6 controllerUserSearch_6 = loader.getController();
        controllerUserSearch_6.setUser(this.user);
        controllerUserSearch_6.setUserRequest(this.userRequest);
        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
