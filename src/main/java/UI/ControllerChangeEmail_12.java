package UI;

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

public class ControllerChangeEmail_12 {

    // Attributes
    Parent root;
    Stage stage;
    Scene scene;
    private UserRequest userRequest;
    private User user;
    @FXML
    private TextField currentEmail;
    @FXML
    private TextField newEmail;
    @FXML
    private Label message;

    // Public Functions

    public void confirm(ActionEvent event) throws IOException {
        if (!currentEmail.getText().isBlank() && !newEmail.getText().isBlank()) {
            String current = this.currentEmail.getText();
            if (this.userRequest.checkCurrentEmail(this.user.getUser_id(), current)) {
                String newE = this.newEmail.getText();
                this.userRequest.changeEmail(this.user.getUser_id(), newE);
                message.setText("The Email has been successfully changed!");
            }
            else {
                message.setText("Your current email is wrong!");
            }
        }
        else {
            message.setText("Please fill in the blanks!");
        }
    }
    public void switchToUserEditProfile(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("userEditProfile.fxml"));
        root = loader.load();
        ControllerUserEditProfile_10 controllerUserEditProfile_10 = loader.getController();
        controllerUserEditProfile_10.setUser(this.user);
        controllerUserEditProfile_10.setUserRequest(this.userRequest);
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
