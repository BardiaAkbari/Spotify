package UI;

import Shared.UserRequest;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.*;
import java.net.Socket;

public class Main extends Application {
    public static void main(String[] args) {
        launch(args);
    }
    @Override
    public void start(Stage primaryStage) {
        // Socket Connecting
        final int PORT = 6666;
        try {
            Socket socket = new Socket("localhost", PORT);
            DataInputStream input = new DataInputStream(socket.getInputStream());
            DataOutputStream output = new DataOutputStream(socket.getOutputStream());
            UserRequest userRequest = new UserRequest(input, output, socket);
            // UI
            primaryStage.setTitle("Spotify");
            primaryStage.getIcons().add(new Image("D:\\SBU\\Term 2\\AP\\Assignments\\Spotify\\src\\main\\resources\\UI\\spotify-icon-marilyn-scott-0.png"));
            FXMLLoader loader = new FXMLLoader(getClass().getResource("scene1.fxml"));
            Parent root = loader.load();
            ControllerScene1_1 controllerScene1 = loader.getController();
            controllerScene1.setUserRequest(userRequest);
            Scene scene = new Scene(root);
            primaryStage.setScene(scene);
            primaryStage.show();
        }
        catch (Exception exception) {
            exception.printStackTrace();
        }
    }
}
