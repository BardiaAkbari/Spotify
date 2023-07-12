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
import javafx.scene.control.PasswordField;
import javafx.stage.Stage;

import java.io.IOException;

public class ControllerChangePassword_11 {

    // Attributes
    Parent root;
    Stage stage;
    Scene scene;
    private UserRequest userRequest;
    private User user;
    @FXML
    PasswordField currentPassword;
    @FXML
    PasswordField newPassword;
    @FXML
    PasswordField confirmPassword;
    @FXML
    Label message;

    // Public Functions
    public void confirm(ActionEvent event) throws IOException {

        if (!currentPassword.getText().isBlank() && !newPassword.getText().isBlank() && !confirmPassword.getText().isBlank()) {

            String current = this.currentPassword.getText();
            if (this.userRequest.checkCurrentPassword(this.user.getUser_id(), current)) {
                String newP = this.newPassword.getText();
                String conP = this.confirmPassword.getText();
                if (newP.equals(conP)) {
                    this.userRequest.changePassword(this.user.getUser_id(), newP);
                    this.message.setText("The password has been successfully changed!");
                }
                else {
                    this.message.setText("Your confirm password is not match with new password!");
                }
            }
            else {
                this.message.setText("Your current password is wrong!");
            }
        }
        else {
            this.message.setText("Please fill in the blanks!");
        }
    }

    public void switchToUserEditProfile(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("userEditProfile.fxml"));
        root = loader.load();
        ControllerUserEditProfile_10 controllerUserEditProfile_10 = loader.getController();
        controllerUserEditProfile_10.setUser(this.user);
        controllerUserEditProfile_10.setUserRequest(this.userRequest);
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
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
