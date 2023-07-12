package UI;

import User.User;

import Shared.UserRequest;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class ControllerUserSignIn_3 {

    // Attributes
    Parent root;
    Stage stage;
    Scene scene;
    private UserRequest userRequest;

    @FXML
    TextField usernameField;
    @FXML
    PasswordField passwordField;
    @FXML
    Label warning;

    // Public Functions
    public void switchToUserMenu(ActionEvent event, User user) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("userMenu.fxml"));
        loader.setControllerFactory(type -> {
            if (type == ControllerUserMenu_5.class) {
                return new ControllerUserMenu_5(user, this.userRequest);
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
    public void userSignIn (ActionEvent event) throws IOException {
        if (!usernameField.getText().isBlank() && !passwordField.getText().isBlank()) {
            String username = usernameField.getText();
            String password = passwordField.getText();
            if (userRequest.usernameCheckingRequest(username)) {
                User user = userRequest.checkPasswordForLoginOperation(username, password);
                if(user == null) {
                    warning.setText("Invalid Password!");
                }
                else{
                    switchToUserMenu(event , user);
                }
            }
            else {
                warning.setText("There is no username such this in database!");
            }
        }
        else {
            warning.setText("Please Enter username and password!");
        }
    }
    public void switchToSceneUser(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("sceneUser.fxml"));
        root = loader.load();
        ControllerSceneUser_2 controllerSceneUser = loader.getController();
        controllerSceneUser.setUserRequest(userRequest);
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
