package UI;

import Shared.UserRequest;
import User.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
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
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ControllerSearchUser_9 implements Initializable {

    // Attributes
    Parent root;
    Stage stage;
    Scene scene;

    @FXML
    private TableView<User> tableView;
    @FXML
    private TableColumn<User, String> userColumn;
    @FXML
    private TableColumn<User, String> IDColumn;
    @FXML
    private TextField search;
    @FXML
    private Label message;
    private User user;
    private UserRequest userRequest;

    ObservableList<User> usersObservableList = FXCollections.observableArrayList();

    // Constructor

    public ControllerSearchUser_9(User user, UserRequest userRequest) {
        this.user = user;
        this.userRequest = userRequest;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        userColumn.setCellValueFactory(new PropertyValueFactory<>("username"));
        IDColumn.setCellValueFactory(new PropertyValueFactory<>("user_id"));
        int allUsers = 0;
        try {
            allUsers = userRequest.numberOfAllUsers();
        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }
        for (int i = 1; i <= allUsers; i++) {
            JSONObject jsonObject = null;
            try {
                jsonObject = userRequest.getRow_iUser(i);

            }
            catch (IOException e) {
                throw new RuntimeException(e);
            }
            String ID = jsonObject.getString("user_id");
            String username = jsonObject.getString("username");
            usersObservableList.add(new User(ID, username));
        }
        tableView.setItems(usersObservableList);
        FilteredList<User> filteredList = new FilteredList<>(usersObservableList, b-> true);

        search.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredList.setPredicate(user -> {

                if (newValue.isEmpty() || newValue.isBlank() || newValue == null) {
                    return  true;
                }
                String searchKeyword = newValue.toLowerCase();
                if (user.getUsername().toLowerCase().indexOf(searchKeyword ) > -1){
                    return true;
                }
                else {
                    return false;
                }
            });
        });

        SortedList<User> sortedList = new SortedList<>(filteredList);
        sortedList.comparatorProperty().bind(tableView.comparatorProperty());
        tableView.setItems(sortedList);
    }

    public void follow (ActionEvent event) throws IOException {
        try {
            ObservableList<User> users = tableView.getSelectionModel().getSelectedItems();
            User secondUser = users.get(0);
            message.setText("");
            String user_id_2 = secondUser.getUser_id();
            if (!this.userRequest.checkFollowUser(this.user.getUser_id(), user_id_2)) {
                this.userRequest.addFollowUser(this.user.getUser_id(), user_id_2);
                message.setText(secondUser.getUsername() + " is followed now!");
            }
            else {
                message.setText("You have already followed " + secondUser.getUsername());
            }
        }
        catch (IndexOutOfBoundsException e) {
            message.setText("You did not choose any user!");
        }
    }
    public void visitPage(ActionEvent event) throws IOException {
        try {
            ObservableList<User> users = tableView.getSelectionModel().getSelectedItems();
            User user = users.get(0);
            message.setText("");
            Stage stage = new Stage();
            stage.setTitle("Spotify");
            stage.getIcons().add(new Image("D:\\SBU\\Term 2\\AP\\Assignments\\Spotify\\src\\main\\resources\\UI\\spotify-icon-marilyn-scott-0.png"));
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("userPage.fxml"));
            loader.setControllerFactory(type -> {
                if (type == ControllerUserPage_22.class) {
                    return new ControllerUserPage_22(user, userRequest);
                }
                try {
                    return type.getConstructor().newInstance();
                }
                catch (Exception e) {
                    throw new RuntimeException(e);
                }
            });
            Parent root = loader.load();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.showAndWait();
        }
        catch (IndexOutOfBoundsException e) {
            message.setText("You did not choose any user!");
        }
    }
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
}
