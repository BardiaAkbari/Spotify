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

public class ControllerUserEditProfile_10 {
    Parent root;
    Stage stage;
    Scene scene;
    private UserRequest userRequest;
    private User user;
    public void switchToChangePassword(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("changePassword.fxml"));
        root = loader.load();
        ControllerChangePassword_11 controllerChangePassword_11 = loader.getController();
        controllerChangePassword_11.setUser(this.user);
        controllerChangePassword_11.setUserRequest(this.userRequest);
        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void switchToChangeEmail(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("changeEmail.fxml"));
        root = loader.load();
        ControllerChangeEmail_12 controllerChangeEmail_12 = loader.getController();
        controllerChangeEmail_12.setUser(this.user);
        controllerChangeEmail_12.setUserRequest(this.userRequest);
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

    // Setter
    public void setUserRequest(UserRequest userRequest) {
        this.userRequest = userRequest;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
