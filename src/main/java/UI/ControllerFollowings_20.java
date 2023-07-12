package UI;

import Artist.Artist;
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
import org.json.JSONObject;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ControllerFollowings_20 implements Initializable {

    // Attributes
    Parent root;
    Stage stage;
    Scene scene;
    private User user;
    private UserRequest userRequest;

    @FXML
    private TableView<User> userTableView;
    @FXML
    private TableColumn<User, String> userColumn;
    @FXML
    private TableColumn<User, String> userIDColumn;
    @FXML
    private TableView<Artist> artistTableView;
    @FXML
    private TableColumn<Artist, String> artistColumn;
    @FXML
    private TableColumn<Artist, String> artistIDColumn;
    @FXML
    private TextField userSearch;
    @FXML
    private TextField artistSearch;
    @FXML
    private Label message;
    ObservableList<User> usersObservableList = FXCollections.observableArrayList();
    ObservableList<Artist> artistsObservableList = FXCollections.observableArrayList();

    public ControllerFollowings_20(User user, UserRequest userRequest) {
        this.user = user;
        this.userRequest = userRequest;
    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // User table view
        userColumn.setCellValueFactory(new PropertyValueFactory<>("username"));
        userIDColumn.setCellValueFactory(new PropertyValueFactory<>("user_id"));

        int numberOfUserToUserFollowings = 0;
        try {
            numberOfUserToUserFollowings = this.userRequest.numberOfFollowings_UserToUser(this.user.getUser_id());
        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }
        for (int i = 1; i <= numberOfUserToUserFollowings; i++) {
            JSONObject jsonObject = null;
            try {
                jsonObject = this.userRequest.getRow_i_UsernameOfUserToUserFollowings(i, this.user.getUser_id());
            }
            catch (IOException e) {
                throw new RuntimeException(e);
            }
            String ID = jsonObject.getString("user_id");
            String username = jsonObject.getString("username");
            usersObservableList.add(new User(ID, username));
        }
        userTableView.setItems(usersObservableList);
        FilteredList<User> userFilteredList = new FilteredList<>(usersObservableList, b-> true);

        userSearch.textProperty().addListener((observable, oldValue, newValue) -> {
            userFilteredList.setPredicate(user -> {

                if (newValue.isEmpty() || newValue.isBlank() || newValue == null) {
                    return  true;
                }
                String userSearchKeyword = newValue.toLowerCase();
                if (user.getUsername().toLowerCase().indexOf(userSearchKeyword ) > -1){
                    return true;
                }
                else {
                    return false;
                }
            });
        });

        SortedList<User> userSortedList = new SortedList<>(userFilteredList);
        userSortedList.comparatorProperty().bind(userTableView.comparatorProperty());
        userTableView.setItems(userSortedList);

        // Artist table view
        artistColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        artistIDColumn.setCellValueFactory(new PropertyValueFactory<>("artist_id"));

        int numberOfUserToArtistFollowings = 0;
        try {
            numberOfUserToArtistFollowings = this.userRequest.numberOfFollowings_UserToArtist(this.user.getUser_id());
        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }
        for (int i = 1; i <= numberOfUserToArtistFollowings; i++) {
            JSONObject jsonObject = null;
            try {
                jsonObject = this.userRequest.getRow_i_nameOfUserToArtistFollowings(i, this.user.getUser_id());
            }
            catch (IOException e) {
                throw new RuntimeException(e);
            }
            String id = jsonObject.getString("artist_id");
            String name = jsonObject.getString("name");
            Artist artist = new Artist();
            artist.setArtist_id(id);
            artist.setName(name);
            artistsObservableList.add(artist);
        }

        artistTableView.setItems(artistsObservableList);
        FilteredList<Artist> artistFilteredList = new FilteredList<>(artistsObservableList, b-> true);

        artistSearch.textProperty().addListener((observable, oldValue, newValue) -> {
            artistFilteredList.setPredicate(artist -> {

                if (newValue.isEmpty() || newValue.isBlank() || newValue == null) {
                    return  true;
                }
                String artistSearchKeyword = newValue.toLowerCase();
                if (artist.getName().toLowerCase().indexOf(artistSearchKeyword ) > -1){
                    return true;
                }
                else {
                    return false;
                }
            });
        });

        SortedList<Artist> artistSortedList = new SortedList<>(artistFilteredList);
        artistSortedList.comparatorProperty().bind(artistTableView.comparatorProperty());
        artistTableView.setItems(artistSortedList);
    }
    public void unfollow(ActionEvent event) throws IOException {
        ObservableList<User> users = userTableView.getSelectionModel().getSelectedItems();
        ObservableList<Artist> artists = artistTableView.getSelectionModel().getSelectedItems();
        if (users.size() != 0 && artists.size() != 0) {
            User user = users.get(0);
            Artist artist = artists.get(0);
            unfollowUser(user);
            unfollowArtist(artist);
            message.setText("User : " + user.getUsername() +  " & Artist : " + artist.getName() + " are unfollowed!");
        }
        else if (users.size() != 0 && artists.size() == 0) {
            User user = users.get(0);
            unfollowUser(user);
            message.setText("User : " + user.getUsername() + " is unfollowed!");
        }
        else if (artists.size() != 0 && users.size() == 0) {
            Artist artist = artists.get(0);
            unfollowArtist(artist);
            message.setText("Artist : " + artist.getName() + " is unfollowed!");
        }
        else if (users.size() == 0 && artists.size() == 0) {
            message.setText("You did not choose any account!");
        }
    }
    public void visitUserPage(ActionEvent event) throws IOException {
        try {
            ObservableList<User> users = userTableView.getSelectionModel().getSelectedItems();
            User user = users.get(0);
            message.setText("");
            Stage stage = new Stage();
            stage.setTitle("Spotify");
            stage.getIcons().add(new Image("D:\\SBU\\Term 2\\AP\\Assignments\\Spotify\\src\\main\\resources\\UI\\spotify-icon-marilyn-scott-0.png"));
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("userPage.fxml"));
            loader.setControllerFactory(type -> {
                if (type == ControllerUserPage_22.class) {
                    return new ControllerUserPage_22(user, userRequest);
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
        catch (IndexOutOfBoundsException e) {
            message.setText("You did not choose any user!");
        }
    }
    public void visitArtistPage(ActionEvent event) throws IOException {
        try {
            ObservableList<Artist> artists = artistTableView.getSelectionModel().getSelectedItems();
            Artist artist = artists.get(0);
            message.setText("");
            Stage stage = new Stage();
            stage.setTitle("Spotify");
            stage.getIcons().add(new Image("D:\\SBU\\Term 2\\AP\\Assignments\\Spotify\\src\\main\\resources\\UI\\spotify-icon-marilyn-scott-0.png"));
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("artistPage.fxml"));
            loader.setControllerFactory(type -> {
                if (type == ControllerArtistPage_23.class) {
                    return new ControllerArtistPage_23(this.userRequest, artist);
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
        catch (IndexOutOfBoundsException e) {
            message.setText("You did not choose any artist!");
        }
    }
    public void switchToUserMenu(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("userMenu.fxml"));
        loader.setControllerFactory(type -> {
            if (type == ControllerUserMenu_5.class) {
                return new ControllerUserMenu_5(this.user, this.userRequest);
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
    public void unfollowUser(User anotheruser) throws IOException {
        this.userRequest.unfollow_UserToUser(this.user.getUser_id(), anotheruser.getUser_id());
    }
    public void unfollowArtist(Artist artist) throws IOException {
        this.userRequest.unfollow_UserToArtist(this.user.getUser_id(), artist.getArtist_id());
    }
}
