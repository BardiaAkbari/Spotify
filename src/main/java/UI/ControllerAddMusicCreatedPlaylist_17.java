package UI;

import Artist.Music;
import Artist.PlayList;
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
import java.util.ArrayList;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ControllerAddMusicCreatedPlaylist_17 implements Initializable {
    Parent root;
    Stage stage;
    Scene scene;
    private User user;
    private UserRequest userRequest;
    private PlayList playList;
    @FXML
    private TableView<Music> tableView;
    @FXML
    private TableColumn<Music, String> titleColumn;
    @FXML
    private TableColumn<Music, String> genreColumn;
    @FXML
    private TableColumn<Music, String> albumColumn;
    @FXML
    private TableColumn<Music, String> artistsColumn;
    @FXML
    private TableColumn<Music, String> durationColumn;
    @FXML
    private TableColumn<Music, String> trackIDColumn;
    @FXML
    private TextField search;
    @FXML
    private Label playlistTitle;
    @FXML
    private Label warning;
    ObservableList<Music> musicObservableList = FXCollections.observableArrayList();
    private ArrayList<Music> addedMusics;

    // Constructor

    public ControllerAddMusicCreatedPlaylist_17(User user, UserRequest userRequest, PlayList playList) {
        this.user = user;
        this.userRequest = userRequest;
        this.playList = playList;
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        playlistTitle.setText(playList.getTitle());
        addedMusics = new ArrayList<>();
        titleColumn.setCellValueFactory(new PropertyValueFactory<>("title"));
        genreColumn.setCellValueFactory(new PropertyValueFactory<>("genre"));
        albumColumn.setCellValueFactory(new PropertyValueFactory<>("album"));
        artistsColumn.setCellValueFactory(new PropertyValueFactory<>("artists"));
        durationColumn.setCellValueFactory(new PropertyValueFactory<>("duration"));
        trackIDColumn.setCellValueFactory(new PropertyValueFactory<>("trackID"));
        int allMusicsNumber = 0;
        try {
            allMusicsNumber = userRequest.numberOfAllMusics();
        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }
        for (int i = 1; i <= allMusicsNumber; i++) {
            JSONObject jsonObject = null;
            try {
                jsonObject = userRequest.getRow_iMusic(i);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            String trackID = jsonObject.getString("track_id");
            String title = jsonObject.getString("title");
            String genre = jsonObject.getString("genre");
            String album = jsonObject.getString("album");
            String artists = jsonObject.getString("artist");
            String duration = jsonObject.getString("duration");
            musicObservableList.add(new Music(trackID, title, genre, album, artists, duration));
        }
        tableView.setItems(musicObservableList);

        FilteredList<Music> filteredList = new FilteredList<>(musicObservableList, b-> true);

        search.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredList.setPredicate(music -> {

                if (newValue.isEmpty() || newValue.isBlank() || newValue == null) {
                    return  true;
                }

                String searchKeyword = newValue.toLowerCase();
                if (music.getTitle().toLowerCase().indexOf(searchKeyword ) > -1){
                    return true;
                }
                else if (music.getGenre().toLowerCase().indexOf(searchKeyword) > -1) {
                    return true;
                }
                else if (music.getAlbum().toLowerCase().indexOf(searchKeyword) > -1) {
                    return true;
                }
                else if (music.getArtists().toLowerCase().indexOf(searchKeyword) > -1) {
                    return true;
                }
                else {
                    return false;
                }
            });
        });
        SortedList<Music> sortedList = new SortedList<>(filteredList);
        sortedList.comparatorProperty().bind(tableView.comparatorProperty());
        tableView.setItems(sortedList);
    }
    public void addButton(ActionEvent event) {
        try {
            ObservableList<Music> selectedItems = tableView.getSelectionModel().getSelectedItems();
            Music music = selectedItems.get(0);
            if (addedMusics.size() == 0) {
                addedMusics.add(music);
                warning.setText(music.getTitle() + " is added to " + playlistTitle.getText());
                System.out.println(music.getTrackID());
            }
            else {
                for (int i = 0; i < addedMusics.size(); i++) {
                    if (addedMusics.get(i).getTrackID().equals(music.getTrackID())) {
                        System.out.println(music.getTrackID());
                        warning.setText("You have already added " + music.getTitle() + " to your playlist");
                        return;
                    }
//                    if (!addedMusics.get(i).getTrackID().equals(music.getTrackID()) && i == addedMusics.size() - 1) {
//                        addedMusics.add(music);
//                        warning.setText(music.getTitle() + " is added to " + playlistTitle.getText());
//                        System.out.println(music.getTrackID());
//                    }
                }
                addedMusics.add(music);
                warning.setText(music.getTitle() + " is added to " + playlistTitle.getText() + "!");
            }
        }
        catch (IndexOutOfBoundsException e) {
            warning.setText("You did not choose any music to add!");
        }
    }
    public void doneButton(ActionEvent event) throws IOException {
        // the playlist is created and it must be added to the database music
        if (addedMusics.size() != 0) {
            for (int i = 0; i < addedMusics.size(); i++) {
                Music myMusic = addedMusics.get(i);
                System.out.println("Music ID : " + myMusic.getTrackID());
                System.out.println("Playlist ID : " + this.playList.getPlayListId());
                this.userRequest.addMusicToPlayList(this.playList.getPlayListId(), myMusic.getTrackID());
            }
            FXMLLoader loader = new FXMLLoader(getClass().getResource("userPlaylist.fxml"));
            root = loader.load();
            ControllerUserPlaylist_13 controllerUserPlaylist_13 = loader.getController();
            controllerUserPlaylist_13.setUser(this.user);
            controllerUserPlaylist_13.setUserRequest(this.userRequest);
            stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }
        else {
            warning.setText("You did not add any music to your new playlist!");
        }
    }
    public void switchToCreatePlaylist(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("createPlaylist.fxml"));
        root = loader.load();
        ControllerCreatePlaylist_16 controllerCreatePlaylist = loader.getController();
        controllerCreatePlaylist.setUser(this.user);
        controllerCreatePlaylist.setUserRequest(this.userRequest);
        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
