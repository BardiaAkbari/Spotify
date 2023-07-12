package Artist;

import java.util.UUID;

public class Artist {

    // Attributes
    private String artist_id;

    public String name;
    private String password;
    private String emailAddress;
    public String biography;
    // private String social media links
    // list of albums and playlist

    /*Constructor*/

    // Constructor For Login

    public Artist(String artist_id, String name, String password, String emailAddress, String biography) {
        this.artist_id = artist_id;
        this.name = name;
        this.password = password;
        this.emailAddress = emailAddress;
        this.biography = biography;
    }

    // Constructor For Signup

    public Artist(String name, String password, String emailAddress, String biography) {
        this.name = name;
        this.password = password;
        this.emailAddress = emailAddress;
        this.biography = biography;
    }

    // Constructor For Observable
    public Artist(String artist_id, String name, String biography) {
        this.artist_id = artist_id;
        this.name = name;
        this.biography = biography;
    }
    public Artist(String name, String biography){
        this.name = name;
        this.biography = biography;
    }
    public Artist(String name) {
        this.name = name;
    }
    public Artist() {

    }
    // Getter


    public String getArtist_id() {
        return artist_id;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public String getBiography() {
        return biography;
    }

    public void setArtist_id(String artist_id) {
        this.artist_id = artist_id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public void setBiography(String biography) {
        this.biography = biography;
    }
}
