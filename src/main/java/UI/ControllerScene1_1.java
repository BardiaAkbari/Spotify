package UI;

import Shared.UserRequest;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class ControllerScene1_1 {

    // Attributes
    Parent root;
    Stage stage;
    Scene scene;
    private UserRequest userRequest;

    // Public Functions
    public void switchToSceneUser(ActionEvent event) throws IOException {
        userRequest.sendUserPosition();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("sceneUser.fxml"));
        root = loader.load();
        ControllerSceneUser_2 controllerSceneUser = loader.getController();
        controllerSceneUser.setUserRequest(userRequest);
        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    public void switchToSceneArtist(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("scene3.fxml"));
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
