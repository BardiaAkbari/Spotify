package UI;

import Artist.Artist;
import Artist.PlayList;
import Artist.Music;
import Shared.UserRequest;
import User.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
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

public class ControllerUserPage_22 implements Initializable {

    // Attributes
    private User selectedUser;
    private UserRequest userRequest;
    @FXML
    private Label usernameLabel;
    @FXML
    private TableView<User> followersTableView;
    @FXML
    private TableColumn<User, String> followersColumn;
    @FXML
    private TableColumn<User, String> followersIDColumn;
    @FXML
    private TextField followersSearch;
    ObservableList<User> followersObservableList = FXCollections.observableArrayList();
    @FXML
    private TableView<User> userFollowingsTableView;
    @FXML
    private TableColumn<User, String> userFollowingsColumn;
    @FXML
    private TableColumn<User, String> userFollowingsIDColumn;
    @FXML
    private TextField userFollowingsSearch;
    ObservableList<User> userFollowingsObservableList = FXCollections.observableArrayList();
    @FXML
    private TableView<Artist> artistFollowingsTableView;
    @FXML
    private TableColumn<Artist, String> artistFollowingsColumn;
    @FXML
    private TableColumn<Artist, String> artistFollowingsIDColumn;
    @FXML
    private TextField artistFollowingsSearch;
    ObservableList<Artist> artistFollowingsObservableList = FXCollections.observableArrayList();
    @FXML
    private TableView<PlayList> playlistsTableView;
    @FXML
    private TableColumn<PlayList, String> playlistsColumn;
    @FXML
    private TableColumn<PlayList, String> playlistsIDColumn;
    @FXML
    private TextField playlistsSearch;
    ObservableList<PlayList> playlistsObservableList = FXCollections.observableArrayList();
    @FXML
    private TableView<Music> musicTableView;
    @FXML
    private TableColumn<Music, String> musicTitleColumn;
    @FXML
    private TableColumn<Music, String> musicArtistColumn;
    @FXML
    private TextField musicsSearch;
    ObservableList<Music> musicsObservableList = FXCollections.observableArrayList();
    @FXML
    private TableView<Music> likesTableView;
    @FXML
    private TableColumn<Music, String> titleColumn;
    @FXML
    private TableColumn<Music, String> artistsColumn;
    @FXML
    private TextField likesSearch;
    ObservableList<Music> likesObservableList = FXCollections.observableArrayList();


    // Constructor
    public ControllerUserPage_22(User selectedUser, UserRequest userRequest) {
        this.selectedUser = selectedUser;
        this.userRequest = userRequest;
    }

    // Public Functions
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        usernameLabel.setText(this.selectedUser.getUsername());

        // Followers
        followersColumn.setCellValueFactory(new PropertyValueFactory<>("username"));
        followersIDColumn.setCellValueFactory(new PropertyValueFactory<>("user_id"));

        int numberOfUserToUserFollowers = 0;
        try {
            numberOfUserToUserFollowers = this.userRequest.numberOfFollowers_UserToUser(this.selectedUser.getUser_id());
        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }
        for (int i = 1; i <= numberOfUserToUserFollowers; i++) {
            JSONObject jsonObject = null;
            try {
                jsonObject = this.userRequest.getRow_i_usernameOfUserToUserFollowers(i, this.selectedUser.getUser_id());
            }
            catch (IOException e) {
                throw new RuntimeException(e);
            }
            String ID = jsonObject.getString("user_id");
            String username = jsonObject.getString("username");
            followersObservableList.add(new User(ID, username));
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

        // User Followings
        userFollowingsColumn.setCellValueFactory(new PropertyValueFactory<>("username"));
        userFollowingsIDColumn.setCellValueFactory(new PropertyValueFactory<>("user_id"));

        int numberOfUserToUserFollowings = 0;
        try {
            numberOfUserToUserFollowings = this.userRequest.numberOfFollowings_UserToUser(this.selectedUser.getUser_id());
        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }
        for (int i = 1; i <= numberOfUserToUserFollowings; i++) {
            JSONObject jsonObject = null;
            try {
                jsonObject = this.userRequest.getRow_i_UsernameOfUserToUserFollowings(i, this.selectedUser.getUser_id());
            }
            catch (IOException e) {
                throw new RuntimeException(e);
            }
            String ID = jsonObject.getString("user_id");
            String username = jsonObject.getString("username");
            userFollowingsObservableList.add(new User(ID, username));
        }

        userFollowingsTableView.setItems(userFollowingsObservableList);
        FilteredList<User> userFollowingsFilteredList = new FilteredList<>(userFollowingsObservableList, b-> true);

        userFollowingsSearch.textProperty().addListener((observable, oldValue, newValue) -> {
            userFollowingsFilteredList.setPredicate(user -> {

                if (newValue.isEmpty() || newValue.isBlank() || newValue == null) {
                    return  true;
                }
                String userFollowingsSearchKeyword = newValue.toLowerCase();
                if (user.getUsername().toLowerCase().indexOf(userFollowingsSearchKeyword) > -1){
                    return true;
                }
                else {
                    return false;
                }
            });
        });

        SortedList<User> userFollowingsSortedList = new SortedList<>(userFollowingsFilteredList);
        userFollowingsSortedList.comparatorProperty().bind(userFollowingsTableView.comparatorProperty());
        userFollowingsTableView.setItems(userFollowingsSortedList);

        // Artist Followings
        artistFollowingsColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        artistFollowingsIDColumn.setCellValueFactory(new PropertyValueFactory<>("artist_id"));

        int numberOfUserToArtistFollowings = 0;
        try {
            numberOfUserToArtistFollowings = this.userRequest.numberOfFollowings_UserToArtist(
                    this.selectedUser.getUser_id());
        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }
        for (int i = 1; i <= numberOfUserToArtistFollowings; i++) {
            JSONObject jsonObject = null;
            try {
                jsonObject = this.userRequest.getRow_i_nameOfUserToArtistFollowings(i,
                        this.selectedUser.getUser_id());
            }
            catch (IOException e) {
                throw new RuntimeException(e);
            }
            String id = jsonObject.getString("artist_id");
            String name = jsonObject.getString("name");
            Artist artist = new Artist();
            artist.setArtist_id(id);
            artist.setName(name);
            artistFollowingsObservableList.add(artist);
        }

        artistFollowingsTableView.setItems(artistFollowingsObservableList);
        FilteredList<Artist> artistFollowingsFilteredList = new FilteredList<>(artistFollowingsObservableList, b-> true);

        artistFollowingsSearch.textProperty().addListener((observable, oldValue, newValue) -> {
            artistFollowingsFilteredList.setPredicate(artist -> {

                if (newValue.isEmpty() || newValue.isBlank() || newValue == null) {
                    return  true;
                }
                String artistFollowingsSearchKeyword = newValue.toLowerCase();
                if (artist.getName().toLowerCase().indexOf(artistFollowingsSearchKeyword) > -1){
                    return true;
                }
                else {
                    return false;
                }
            });
        });

        SortedList<Artist> artistFollowingsSortedList = new SortedList<>(artistFollowingsFilteredList);
        artistFollowingsSortedList.comparatorProperty().bind(artistFollowingsTableView.comparatorProperty());
        artistFollowingsTableView.setItems(artistFollowingsSortedList);

        // PlayLists
        playlistsColumn.setCellValueFactory(new PropertyValueFactory<>("title"));
        playlistsIDColumn.setCellValueFactory(new PropertyValueFactory<>("playListId"));

        int numberOfAllPlayListOfUser = 0;
        try {
            numberOfAllPlayListOfUser = this.userRequest.numberOfAllPlayListForSpecificUser(this.selectedUser.getUser_id());
        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }
        for (int i = 1; i <= numberOfAllPlayListOfUser; i++) {
            JSONObject jsonObject;
            try {
                jsonObject = new JSONObject(this.userRequest.getRow_iPlayList(i, this.selectedUser.getUser_id()));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            this.playlistsObservableList.add(new PlayList(jsonObject.getString("playlist_id"),
                    jsonObject.getString("title")));
        }



        playlistsTableView.setItems(playlistsObservableList);
        FilteredList<PlayList> playlistsFilteredList = new FilteredList<>(playlistsObservableList, b-> true);

        playlistsSearch.textProperty().addListener((observable, oldValue, newValue) -> {
            playlistsFilteredList.setPredicate( playList -> {

                if (newValue.isEmpty() || newValue.isBlank() || newValue == null) {
                    return  true;
                }
                String playlistsSearchKeyword = newValue.toLowerCase();
                if (playList.getTitle().toLowerCase().indexOf(playlistsSearchKeyword) > -1){
                    return true;
                }
                else {
                    return false;
                }
            });
        });

        SortedList<PlayList> playListsSortedList = new SortedList<>(playlistsFilteredList);
        playListsSortedList.comparatorProperty().bind(playlistsTableView.comparatorProperty());
        playlistsTableView.setItems(playListsSortedList);

        // Likes
        titleColumn.setCellValueFactory(new PropertyValueFactory<>("title"));
        artistsColumn.setCellValueFactory(new PropertyValueFactory<>("artists"));

        int allLikedMusicsUserNum = 0;
        try {
            allLikedMusicsUserNum = this.userRequest.numberOfUserLike(this.selectedUser.getUser_id());
        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }
        for (int i = 1; i <= allLikedMusicsUserNum; i++) {
            JSONObject jsonObject = null;
            try {
                jsonObject = this.userRequest.getRow_i_UserLike(i, this.selectedUser.getUser_id());
            }
            catch (IOException e) {
                throw new RuntimeException(e);
            }
            String title = jsonObject.getString("title");
            String artists = jsonObject.getString("artist");
            likesObservableList.add(new Music(title, artists));
        }



        likesTableView.setItems(likesObservableList);
        FilteredList<Music> likesFilteredList = new FilteredList<>(likesObservableList, b-> true);

        likesSearch.textProperty().addListener((observable, oldValue, newValue) -> {
            likesFilteredList.setPredicate( music -> {

                if (newValue.isEmpty() || newValue.isBlank() || newValue == null) {
                    return  true;
                }
                String likesSearchKeyword = newValue.toLowerCase();
                if (music.getTitle().toLowerCase().indexOf(likesSearchKeyword) > -1){
                    return true;
                }
                else {
                    return false;
                }
            });
        });

        SortedList<Music> likesSortedList = new SortedList<>(likesFilteredList);
        likesSortedList.comparatorProperty().bind(likesTableView.comparatorProperty());
        likesTableView.setItems(likesSortedList);
    }
    public void viewPlaylist(ActionEvent event) {
        if (musicsObservableList.size() == 0) {
            try {
                ObservableList<PlayList> playLists = playlistsTableView.getSelectionModel().getSelectedItems();
                PlayList playList = playLists.get(0);

                // Musics Of PlayList
                musicTitleColumn.setCellValueFactory(new PropertyValueFactory<>("title"));
                musicArtistColumn.setCellValueFactory(new PropertyValueFactory<>("artists"));

                int allMusicsNumber = 0;
                try {
                    allMusicsNumber = this.userRequest.numberOfAllMusicsForSpecificPlayList(playList.getPlayListId());
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                for (int i = 1; i <= allMusicsNumber; i++) {
                    JSONObject jsonObject = null;
                    try {
                        jsonObject = userRequest.getRow_iMusicFromPlayList(i, playList.getPlayListId());
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                    String title = jsonObject.getString("title");
                    String artists = jsonObject.getString("artist");
                    musicsObservableList.add(new Music(title, artists));
                }




                musicTableView.setItems(musicsObservableList);
                FilteredList<Music> musicsFilteredList = new FilteredList<>(musicsObservableList, b-> true);

                musicsSearch.textProperty().addListener((observable, oldValue, newValue) -> {
                    musicsFilteredList.setPredicate( music -> {

                        if (newValue.isEmpty() || newValue.isBlank() || newValue == null) {
                            return  true;
                        }
                        String musicsSearchKeyword = newValue.toLowerCase();
                        if (music.getTitle().toLowerCase().indexOf(musicsSearchKeyword) > -1){
                            return true;
                        }
                        else {
                            return false;
                        }
                    });
                });

                SortedList<Music> musicsSortedList = new SortedList<>(musicsFilteredList);
                musicsSortedList.comparatorProperty().bind(musicTableView.comparatorProperty());
                musicTableView.setItems(musicsSortedList);

            }
            catch (IndexOutOfBoundsException e) {

            }
        }
        else {
            musicsObservableList = FXCollections.observableArrayList();
            try {
                ObservableList<PlayList> playLists = playlistsTableView.getSelectionModel().getSelectedItems();
                PlayList playList = playLists.get(0);

                // Musics Of PlayList
                musicTitleColumn.setCellValueFactory(new PropertyValueFactory<>("title"));
                musicArtistColumn.setCellValueFactory(new PropertyValueFactory<>("artists"));

                int allMusicsNumber = 0;
                try {
                    allMusicsNumber = this.userRequest.numberOfAllMusicsForSpecificPlayList(playList.getPlayListId());
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                for (int i = 1; i <= allMusicsNumber; i++) {
                    JSONObject jsonObject = null;
                    try {
                        jsonObject = userRequest.getRow_iMusicFromPlayList(i, playList.getPlayListId());
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                    String title = jsonObject.getString("title");
                    String artists = jsonObject.getString("artist");
                    musicsObservableList.add(new Music(title, artists));
                }




                musicTableView.setItems(musicsObservableList);
                FilteredList<Music> musicsFilteredList = new FilteredList<>(musicsObservableList, b-> true);

                musicsSearch.textProperty().addListener((observable, oldValue, newValue) -> {
                    musicsFilteredList.setPredicate( music -> {

                        if (newValue.isEmpty() || newValue.isBlank() || newValue == null) {
                            return  true;
                        }
                        String musicsSearchKeyword = newValue.toLowerCase();
                        if (music.getTitle().toLowerCase().indexOf(musicsSearchKeyword) > -1){
                            return true;
                        }
                        else {
                            return false;
                        }
                    });
                });

                SortedList<Music> musicsSortedList = new SortedList<>(musicsFilteredList);
                musicsSortedList.comparatorProperty().bind(musicTableView.comparatorProperty());
                musicTableView.setItems(musicsSortedList);

            }
            catch (IndexOutOfBoundsException e) {

            }

        }
    }
}
