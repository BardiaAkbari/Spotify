package Artist;

import java.util.UUID;

public class PlayList {

    // Attributes
    private String playListId;
    private String user_id;
    private String title;

    // Constructor
    public PlayList(String playListId, String title) {
        this.playListId = playListId;
        this.title = title;
    }

    public PlayList(String title) {
        this.playListId = UUID.randomUUID().toString();
        this.title = title;
    }

    //Setter
    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    // Getter
    public String getTitle() {
        return title;
    }
    public String getPlayListId() {
        return  playListId;
    }

    // To_String
    @Override
    public String toString() {
        return "PlayList{" +
                "playListId='" + playListId + '\'' +
                ", user_id='" + user_id + '\'' +
                ", title='" + title + '\'' +
                '}';
    }
}
