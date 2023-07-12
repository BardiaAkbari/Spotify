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
import javafx.stage.Stage;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ControllerFollowers_21 implements Initializable {
    // Atributtes
    Parent root;
    Stage stage;
    Scene scene;
    private User user;
    private UserRequest userRequest;

    @FXML
    private TableView<User> tableView;
    @FXML
    private TableColumn<User, String> userColumn;
    @FXML
    private TableColumn<User, String> userIDColumn;
    @FXML
    private TextField search;
    @FXML
    private Label message;
    ObservableList<User> usersObservableList = FXCollections.observableArrayList();

    public ControllerFollowers_21(User user, UserRequest userRequest) {
        this.user = user;
        this.userRequest = userRequest;
    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {

        userColumn.setCellValueFactory(new PropertyValueFactory<>("username"));
        userIDColumn.setCellValueFactory(new PropertyValueFactory<>("user_id"));

        int numberOfUserToUserFollowers = 0;
        try {
            numberOfUserToUserFollowers = this.userRequest.numberOfFollowers_UserToUser(this.user.getUser_id());
        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }
        for (int i = 1; i <= numberOfUserToUserFollowers; i++) {
            JSONObject jsonObject = null;
            try {
                jsonObject = this.userRequest.getRow_i_usernameOfUserToUserFollowers(i, this.user.getUser_id());
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
    public void remove(ActionEvent event) {
        try {
            ObservableList<User> users = tableView.getSelectionModel().getSelectedItems();
            User Anotheruser = users.get(0);
            message.setText("");
            String username = Anotheruser.getUsername();
            this.userRequest.unfollow_UserToUser(Anotheruser.getUser_id(), this.user.getUser_id());
            message.setText(username + " is removed from your followers!");
        }
        catch (IndexOutOfBoundsException e) {
            message.setText("You did not choose any user!");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public void visitPage(ActionEvent event) {

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
}
