package UI;

import Artist.PlayList;
import Shared.UserRequest;
import User.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class ControllerCreatePlaylist_16 {

    // Attributes
    Parent root;
    Stage stage;
    Scene scene;
    private User user;
    private UserRequest userRequest;
    @FXML
    private TextField playlistTitle;
    @FXML
    private Label message;
    public void setUser(User user) {
        this.user = user;
    }
    public void setUserRequest(UserRequest userRequest) {
        this.userRequest = userRequest;
    }

    // Public Functions
    public void nextButton(ActionEvent event) throws IOException {
        if (!playlistTitle.getText().isBlank()) {
            String title = playlistTitle.getText();
            if (this.userRequest.checkPlayListExist(title)) {
                message.setText("This title has been chosen!");
            }
            else {
                PlayList playList = new PlayList(title);
                playList.setUser_id(this.user.getUser_id());
                this.userRequest.addPlayList(playList.getPlayListId(), this.user.getUser_id(), title);
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("addMusicToCreatedPlaylist.fxml"));
                loader.setControllerFactory(type -> {
                    if (type == ControllerAddMusicCreatedPlaylist_17.class) {
                        return new ControllerAddMusicCreatedPlaylist_17(this.user, this.userRequest, playList);
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
    }
    public void switchToUserPlaylist(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("userPlaylist.fxml"));
        root = loader.load();
        ControllerUserPlaylist_13 controllerUserPlaylist_13 = loader.getController();
        controllerUserPlaylist_13.setUser(this.user);
        controllerUserPlaylist_13.setUserRequest(this.userRequest);
        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
