package UI;

import Shared.UserRequest;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class ControllerSceneUser_2 {

    // Attributes
    Parent root;
    Stage stage;
    Scene scene;
    private UserRequest userRequest;

    // Public Functions
    public void switchToUserSignIn(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("userSignIn.fxml"));
        root = loader.load();
        ControllerUserSignIn_3 controllerUserSignIn = loader.getController();
        controllerUserSignIn.setUserRequest(userRequest);
        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    public void switchToUserSignUp(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("userSignUp.fxml"));
        root = loader.load();
        ControllerUserSignUp_4 controllerUserSignUp = loader.getController();
        controllerUserSignUp.setUserRequest(userRequest);
        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    public void switchToScene1 (ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("scene1.fxml"));
        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    // Setter

    public void setUserRequest(UserRequest userRequest) {
        this.userRequest = userRequest;
    }
}
