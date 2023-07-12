package UI;

import Artist.Artist;
import Artist.Music;
import Shared.UserRequest;
import User.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ControllerArtistPage_23 implements Initializable {
    private Artist artist;
    private UserRequest userRequest;
    @FXML
    private Label nameLabel;
    @FXML
    private TableView<User> followersTableView;
    @FXML
    private TableColumn<User, String> followersColumn;
    @FXML
    private TextField followersSearch;
    ObservableList<User> followersObservableList = FXCollections.observableArrayList();
    @FXML
    private TableView<Music> musicsTableView;
    @FXML
    private TableColumn<Music, String> titleColumn;
    @FXML
    private TableColumn<Music, String> albumColumn;
    @FXML
    private TableColumn<Music, String> artistsColumn;
    @FXML
    private TextField musicsSearch;
    ObservableList<Music> musicsObservableList = FXCollections.observableArrayList();

    public ControllerArtistPage_23(UserRequest userRequest, Artist artist) {
        this.userRequest = userRequest;
        this.artist = artist;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        nameLabel.setText(this.artist.getName());

        // Followers
        followersColumn.setCellValueFactory(new PropertyValueFactory<>("username"));

        int numberOfFollowersUserArtist = 0;
        try {
            numberOfFollowersUserArtist = this.userRequest.numberOfArtistFollowers(this.artist.getArtist_id());
        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }
        for (int i = 1; i <= numberOfFollowersUserArtist; i++) {
            String username = null;
            try {
                username = this.userRequest.getRow_i_ArtistFollower(i, this.artist.getArtist_id());
            }
            catch (IOException e) {
                throw new RuntimeException(e);
            }
            followersObservableList.add(new User(username));
        }

        followersTableView.setItems(followersObservableList);
        FilteredList<User> followersFilteredList = new FilteredList<>(followersObservableList, b-> true);

        followersSearch.textProperty().addListener((observable, oldValue, newValue) -> {
            followersFilteredList.setPredicate(user -> {

                if (newValue.isEmpty() || newValue.isBlank() || newValue == null) {
                    return  true;
                }
                String followersSearchKeyword = newValue.toLowerCase();
                if (user.getUsername().toLowerCase().indexOf(followersSearchKeyword) > -1){
                    return true;
                }
                else {
                    return false;
                }
            });
        });

        SortedList<User> followersSortedList = new SortedList<>(followersFilteredList);
        followersSortedList.comparatorProperty().bind(followersTableView.comparatorProperty());
        followersTableView.setItems(followersSortedList);

        titleColumn.setCellValueFactory(new PropertyValueFactory<>("title"));
        albumColumn.setCellValueFactory(new PropertyValueFactory<>("album"));
        artistsColumn.setCellValueFactory(new PropertyValueFactory<>("artists"));

        int allMusicsNumberForSpecificArtist = 0;
        try {
            allMusicsNumberForSpecificArtist = this.userRequest.numberOfMusicForSpecificArtist(
                    this.artist.getArtist_id());
        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }
        for (int i = 1; i <= allMusicsNumberForSpecificArtist; i++) {
            JSONObject jsonObject = null;
            try {
                jsonObject = this.userRequest.getRow_i_MusicArtist(i, this.artist.getArtist_id());
            }
            catch (IOException e) {
                throw new RuntimeException(e);
            }
            String title = jsonObject.getString("title");
            String album = jsonObject.getString("album");
            String artists = jsonObject.getString("artist");
            musicsObservableList.add(new Music(title, album, artists));
        }

        musicsTableView.setItems(musicsObservableList);
        FilteredList<Music> musicsfilteredList = new FilteredList<>(musicsObservableList, b-> true);

        musicsSearch.textProperty().addListener((observable, oldValue, newValue) -> {
            musicsfilteredList.setPredicate(music -> {

                if (newValue.isEmpty() || newValue.isBlank() || newValue == null) {
                    return  true;
                }
                String musicsSearchKeyword = newValue.toLowerCase();
                if (music.getTitle().toLowerCase().indexOf(musicsSearchKeyword) > -1){
                    return true;
                }
                else if (music.getAlbum().toLowerCase().indexOf(musicsSearchKeyword) > -1) {
                    return true;
                }
                else if (music.getArtists().toLowerCase().indexOf(musicsSearchKeyword) > -1) {
                    return true;
                }
                else {
                    return false;
                }
            });
        });
        SortedList<Music> musicsSortedList = new SortedList<>(musicsfilteredList);
        musicsSortedList.comparatorProperty().bind(musicsTableView.comparatorProperty());
        musicsTableView.setItems(musicsSortedList);
    }
}
