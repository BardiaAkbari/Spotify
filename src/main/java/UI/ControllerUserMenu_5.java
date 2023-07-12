package UI;

import Artist.Music;
import Shared.UserRequest;
import User.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import org.json.JSONObject;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.URL;
import java.util.ResourceBundle;

public class ControllerUserMenu_5 implements Initializable {

    // Attributes
    Parent root;
    Stage stage;
    Scene scene;
    private User user;
    private UserRequest userRequest;
    @FXML
    private Label usernameLabel;
    @FXML
    private TableView<Music> tableView;
    @FXML
    private TableColumn<Music, String> titleColumn;
    @FXML
    private TableColumn<Music, String> artistsColumn;
    ObservableList<Music> likesObservableList = FXCollections.observableArrayList();

    public ControllerUserMenu_5(User user, UserRequest userRequest) {
        this.user = user;
        this.userRequest = userRequest;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        usernameLabel.setText(this.user.getUsername());
        titleColumn.setCellValueFactory(new PropertyValueFactory<>("title"));
        artistsColumn.setCellValueFactory(new PropertyValueFactory<>("artists"));

        int allLikedMusicsUserNum = 0;
        try {
            allLikedMusicsUserNum = this.userRequest.numberOfUserLike(this.user.getUser_id());
        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }
        for (int i = 1; i <= allLikedMusicsUserNum; i++) {
            JSONObject jsonObject = null;
            try {
                jsonObject = this.userRequest.getRow_i_UserLike(i, this.user.getUser_id());
            }
            catch (IOException e) {
                throw new RuntimeException(e);
            }
            String title = jsonObject.getString("title");
            String artists = jsonObject.getString("artist");
            likesObservableList.add(new Music(title, artists));
        }
        tableView.setItems(likesObservableList);
    }

    // Public Functions
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
    public void switchToUserPlaylist(ActionEvent event) throws IOException{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("userPlaylist.fxml"));
        root = loader.load();
        ControllerUserPlaylist_13 controllerUserPlaylist = loader.getController();
        controllerUserPlaylist.setUser(this.user);
        controllerUserPlaylist.setUserRequest(this.userRequest);
        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    public void switchToUserEditProfile(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("userEditProfile.fxml"));
        root = loader.load();
        ControllerUserEditProfile_10 controllerUserEditProfile_10 = loader.getController();
        controllerUserEditProfile_10.setUser(user);
        controllerUserEditProfile_10.setUserRequest(this.userRequest);
        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    public void switchToScene1(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("scene1.fxml"));
        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    public void switchToUserFollowings(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("followings.fxml"));
        loader.setControllerFactory(type -> {
            if (type == ControllerFollowings_20.class) {
                return new ControllerFollowings_20(this.user, this.userRequest);
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
    public void switchToUserFollowers(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("followers.fxml"));
        loader.setControllerFactory(type -> {
            if (type == ControllerFollowers_21.class) {
                return new ControllerFollowers_21(this.user, this.userRequest);
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
