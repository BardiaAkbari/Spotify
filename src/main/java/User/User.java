package User;

import java.util.UUID;

public class User {

    // Attributes

    private String user_id;
    private String username;
    private String password;
    private String emailAddress;

    // private profile pricture;
    // private playlist creates or liked

    /*Constructor*/

    //Constructor For Login
    public User(String user_id, String username, String password, String emailAddress) {
        this.user_id = user_id;
        this.username = username;
        this.password = password;
        this.emailAddress = emailAddress;
    }

    // Constructor For Signup

    public User(String username, String password, String emailAddress) {
        this.user_id = UUID.randomUUID().toString();
        this.username = username;
        this.password = password;
        this.emailAddress = emailAddress;
    }

    // Constructor For Observable
    public User(String user_id, String username) {
        this.user_id = user_id;
        this.username = username;
    }

    public User(String username) {
        this.username = username;
    }

    // Getter

    public String getUser_id() {
        return user_id;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

}
