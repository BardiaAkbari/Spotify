package UI;

import Artist.Artist;
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

public class ControllerSearchArtist_8 implements Initializable {

    // Attributes
    Parent root;
    Stage stage;
    Scene scene;
    private User user;
    private UserRequest userRequest;
    @FXML
    private TableView<Artist> tableView;
    @FXML
    private TableColumn<Artist, String> artistColumn;
    @FXML
    private TableColumn<Artist, String> biographyColumn;
    @FXML
    private TableColumn<Artist, String> IDColumn;
    @FXML
    private TextField search;
    @FXML
    private Label message;

    ObservableList<Artist> artistsObservableList = FXCollections.observableArrayList();

    // Constructor

    public ControllerSearchArtist_8(User user, UserRequest userRequest) {
        this.user = user;
        this.userRequest = userRequest;
    }

    // Public Functions
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        artistColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        biographyColumn.setCellValueFactory(new PropertyValueFactory<>("biography"));
        IDColumn.setCellValueFactory(new PropertyValueFactory<>("artist_id"));
        int allArtists = 0;
        try {
            allArtists = userRequest.numberOfAllArtist();
        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }
        for (int i = 1; i <= allArtists; i++) {
            JSONObject jsonObject = null;
            try {
                jsonObject = userRequest.getRow_iArtist(i);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            String ID = jsonObject.getString("artist_id");
            String name = jsonObject.getString("name");
            String biography = jsonObject.getString("biography");
            artistsObservableList.add(new Artist(ID, name, biography));
        }
        tableView.setItems(artistsObservableList);
        FilteredList<Artist> filteredList = new FilteredList<>(artistsObservableList, b-> true);

        search.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredList.setPredicate(artist -> {

                if (newValue.isEmpty() || newValue.isBlank() || newValue == null) {
                    return  true;
                }

                String searchKeyword = newValue.toLowerCase();
                if (artist.getName().toLowerCase().indexOf(searchKeyword ) > -1){
                    return true;
                }
                else if (artist.getBiography().toLowerCase().indexOf(searchKeyword) > -1) {
                    return true;
                }
                else {
                    return false;
                }
            });
        });

        SortedList<Artist> sortedList = new SortedList<>(filteredList);
        sortedList.comparatorProperty().bind(tableView.comparatorProperty());
        tableView.setItems(sortedList);

    }

    public void follow (ActionEvent event) throws IOException {
        try {
            ObservableList<Artist> artists = tableView.getSelectionModel().getSelectedItems();
            Artist artist = artists.get(0);
            message.setText("");
            String artistID = artist.getArtist_id();
            if (!this.userRequest.checkFollowArtist(this.user.getUser_id(), artistID)) {
                this.userRequest.addFollowArtist(this.user.getUser_id(), artistID);
                message.setText(artist.getName() + " is followed now!");
            }
            else {
                message.setText("You have already followed " + artist.getName());
            }
        }
        catch (IndexOutOfBoundsException e) {
            message.setText("You did not choose any artist!");
        }
    }
    public void visitPage(ActionEvent event) throws IOException {
        try {
            ObservableList<Artist> artists = tableView.getSelectionModel().getSelectedItems();
            Artist artist = artists.get(0);
            message.setText("");
            Stage stage = new Stage();
            stage.setTitle("Spotify");
            stage.getIcons().add(new Image("D:\\SBU\\Term 2\\AP\\Assignments\\Spotify\\src\\main\\resources\\UI\\spotify-icon-marilyn-scott-0.png"));
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("artistPage.fxml"));
            loader.setControllerFactory(type -> {
                if (type == ControllerArtistPage_23.class) {
                    return new ControllerArtistPage_23(this.userRequest, artist);
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
            message.setText("You did not choose any artist!");
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
