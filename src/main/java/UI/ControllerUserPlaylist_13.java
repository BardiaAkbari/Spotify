package UI;

import Shared.UserRequest;
import User.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class ControllerUserPlaylist_13 {

    // Attributes
    Parent root;
    Stage stage;
    Scene scene;
    private User user;
    private UserRequest userRequest;

    // Public Functions

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

    public void switchToCreatePlaylist(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("createPlaylist.fxml"));
        root = loader.load();
        ControllerCreatePlaylist_16 controllerCreatePlaylist = loader.getController();
        controllerCreatePlaylist.setUser(this.user);
        controllerCreatePlaylist.setUserRequest(this.userRequest);
        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
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

    //Setter
    public void setUser(User user) {
        this.user = user;
    }

    public void setUserRequest(UserRequest userRequest) {
        this.userRequest = userRequest;
    }
}
