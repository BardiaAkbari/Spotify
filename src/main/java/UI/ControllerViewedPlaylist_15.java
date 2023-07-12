package UI;

import Artist.Music;
import Artist.PlayList;
import Shared.UserRequest;
import User.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
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
import org.json.JSONObject;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ControllerViewedPlaylist_15 implements Initializable {

    // Attributes
    Parent root;
    Stage stage;
    Scene scene;
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
    private Label playlistTitle;
    private User user;
    private UserRequest userRequest;
    private PlayList playList;
    private String arr[];
    ObservableList<Music> musicObservableList = FXCollections.observableArrayList();

    // Constructor
    public ControllerViewedPlaylist_15(User user, UserRequest userRequest, PlayList playList) {
        this.user = user;
        this.userRequest = userRequest;
        this.playList = playList;
    }

    public PlayList getPlayList() {
        return playList;
    }

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
            allMusicsNumber = this.userRequest.numberOfAllMusicsForSpecificPlayList(this.playList.getPlayListId());
            this.arr = new String[allMusicsNumber];
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        for (int i = 1; i <= allMusicsNumber; i++) {
            JSONObject jsonObject = null;
            try {
                jsonObject = userRequest.getRow_iMusicFromPlayList(i, this.playList.getPlayListId());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            String trackID = jsonObject.getString("trackID");
            String title = jsonObject.getString("title");
            String genre = jsonObject.getString("genre");
            String album = jsonObject.getString("album");
            String artists = jsonObject.getString("artist");
            String duration = jsonObject.getString("duration");
            this.arr[i - 1] = trackID;
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
        Stage stage = new Stage();
        stage.setTitle("Spotify");
        stage.getIcons().add(new Image("D:\\SBU\\Term 2\\AP\\Assignments\\Spotify\\src\\main\\resources\\UI\\spotify-icon-marilyn-scott-0.png"));
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("playlistPlayer.fxml"));
        loader.setControllerFactory(type -> {
            if (type == ControllerPlaylistPlayer_19.class) {
                return new ControllerPlaylistPlayer_19(userRequest, user, arr);
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

    public void switchToMyPlaylists(ActionEvent event) throws IOException {

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("myPlaylists.fxml"));
        loader.setControllerFactory(type -> {
            if (type == ControllerMyPlaylists_14.class) {
                return new ControllerMyPlaylists_14(this.user, this.userRequest);
            }
            try {
                return type.getConstructor().newInstance();
            }
            catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
        root = loader.load();
        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
