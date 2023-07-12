package Shared;

import User.User;
import org.json.JSONObject;

import java.io.*;
import java.net.Socket;

public class UserRequest {

    // Attributes
    private DataInputStream input;
    private DataOutputStream output;
    private Socket socket;

    public UserRequest(DataInputStream input, DataOutputStream output, Socket socket) {
        this.input = input;
        this.output = output;
        this.socket = socket;
    }


    // Public Functions

    public void sendUserPosition() throws IOException {
        output.writeUTF("2");
        output.flush();
    }

    public boolean usernameCheckingRequest(String username) throws IOException {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("number", "1");
        jsonObject.put("username", username);
        String jsonCommand = jsonObject.toString();
        output.writeUTF(jsonCommand);
        output.flush();
        String answer = input.readUTF();
        return Boolean.parseBoolean(answer);
    }

    public User checkPasswordForLoginOperation (String username, String password) throws IOException {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("number", "2");
        jsonObject.put("username", username);
        jsonObject.put("password", password);
        String jsonCommand = jsonObject.toString();
        output.writeUTF(jsonCommand);
        output.flush();
        String answer = input.readUTF();
        if (answer.equals("")) {
            return null;
        }
        else {
            JSONObject getUser = new JSONObject(answer);
            return new User(getUser.getString("iD"), getUser.getString("username")
                    , getUser.getString("password"), getUser.getString("email_address"));
        }
    }

    public void addClientToDB(User user) throws IOException {
        JSONObject userJson = new JSONObject();
        userJson.put("iD", user.getUser_id());
        userJson.put("username", user.getUsername());
        userJson.put("password", user.getPassword());
        userJson.put("emailAddress", user.getEmailAddress());
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("number", "3");
        jsonObject.put("client", userJson);
        String jsonCommand = jsonObject.toString();
        output.writeUTF(jsonCommand);
        output.flush();
        input.readUTF();
    }

    public int numberOfAllMusics() throws IOException {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("number", "4");
        String jsonCommand = jsonObject.toString();
        this.output.writeUTF(jsonCommand);
        this.output.flush();
        return Integer.parseInt(this.input.readUTF());
    }

    public JSONObject getRow_iMusic(int i) throws IOException {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("number", "5");
        jsonObject.put("row", i);
        String jsonCommand = jsonObject.toString();
        this.output.writeUTF(jsonCommand);
        this.output.flush();
        return new JSONObject(this.input.readUTF());
    }

    public int numberOfAllArtist() throws IOException {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("number", "6");
        String jsonCommand = jsonObject.toString();
        this.output.writeUTF(jsonCommand);
        this.output.flush();
        return Integer.parseInt(this.input.readUTF());
    }

    public JSONObject getRow_iArtist(int i) throws IOException {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("number", "7");
        jsonObject.put("row", i);
        String jsonCommand = jsonObject.toString();
        this.output.writeUTF(jsonCommand);
        this.output.flush();
        return new JSONObject(this.input.readUTF());
    }

    public int numberOfAllUsers() throws IOException {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("number", "8");
        String jsonCommand = jsonObject.toString();
        this.output.writeUTF(jsonCommand);
        this.output.flush();
        return Integer.parseInt(this.input.readUTF());
    }

    public JSONObject getRow_iUser(int i) throws IOException {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("number", "9");
        jsonObject.put("row", i);
        String jsonCommand = jsonObject.toString();
        this.output.writeUTF(jsonCommand);
        this.output.flush();
        return new JSONObject(this.input.readUTF());
    }

    public boolean checkCurrentPassword(String user_id, String password) throws IOException {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("number", "10");
        jsonObject.put("user_id", user_id);
        jsonObject.put("password", password);
        String jsonCommand = jsonObject.toString();
        this.output.writeUTF(jsonCommand);
        this.output.flush();
        return this.input.readBoolean();
    }

    public void changePassword(String user_id, String password) throws IOException {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("number", "11");
        jsonObject.put("user_id", user_id);
        jsonObject.put("password", password);
        String jsonCommand = jsonObject.toString();
        this.output.writeUTF(jsonCommand);
        this.output.flush();
        this.input.readUTF();
    }

    public boolean checkCurrentEmail(String user_id, String email) throws IOException {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("number", "12");
        jsonObject.put("user_id", user_id);
        jsonObject.put("email", email);
        String jsonCommand = jsonObject.toString();
        this.output.writeUTF(jsonCommand);
        this.output.flush();
        return this.input.readBoolean();
    }

    public void changeEmail(String user_id, String email) throws IOException {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("number", "13");
        jsonObject.put("user_id", user_id);
        jsonObject.put("email", email);
        String jsonCommand = jsonObject.toString();
        this.output.writeUTF(jsonCommand);
        this.output.flush();
        this.input.readUTF();
    }

    public int numberOfAllPlayListForSpecificUser(String user_id) throws IOException {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("number", "14");
        jsonObject.put("user_id", user_id);
        String jsonCommand = jsonObject.toString();
        this.output.writeUTF(jsonCommand);
        this.output.flush();
        return this.input.readInt();
    }

    public String getRow_iPlayList(int i, String user_id) throws IOException {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("number", "15");
        jsonObject.put("row", i);
        jsonObject.put("user_id", user_id);
        String jsonCommand = jsonObject.toString();
        this.output.writeUTF(jsonCommand);
        this.output.flush();
        return this.input.readUTF();
    }

    public int numberOfAllMusicsForSpecificPlayList(String playListId) throws IOException {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("number", "16");
        jsonObject.put("playListId", playListId);
        String jsonCommand = jsonObject.toString();
        this.output.writeUTF(jsonCommand);
        this.output.flush();
        return this.input.readInt();
    }

    public JSONObject getRow_iMusicFromPlayList(int i, String playListId) throws IOException {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("number", "17");
        jsonObject.put("row", i);
        jsonObject.put("playListId", playListId);
        String jsonCommand = jsonObject.toString();
        this.output.writeUTF(jsonCommand);
        this.output.flush();
        return new JSONObject(this.input.readUTF());
    }

    public boolean checkIfUserDownloadsSpecificMusic(String user_id, String track_id) throws IOException {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("number", "18");
        jsonObject.put("user_id", user_id);
        jsonObject.put("track_id", track_id);
        String jsonCommand = jsonObject.toString();
        this.output.writeUTF(jsonCommand);
        this.output.flush();
        return this.input.readBoolean();
    }

    public void addMusicToFolder(String track_id, String username) throws IOException {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("number", "19");
        jsonObject.put("track_id", track_id);
        String jsonCommand = jsonObject.toString();
        this.output.writeUTF(jsonCommand);
        this.output.flush();
        int fileNameLen = this.input.readInt();
        byte[] fileNameBytes = new byte[fileNameLen];
        this.input.readFully(fileNameBytes);
        String fileName = new String(fileNameBytes);
        int file_content_len = this.input.readInt();
        byte[] file_content = new byte[file_content_len];
        this.input.readFully(file_content, 0, file_content_len);
        File file = new File("D:\\SBU\\Term 2\\AP\\Assignments\\Spotify\\UserDownloads\\" + username);
        if (!file.exists()) {
            boolean creat = file.mkdir();
        }
        String okFilePath = "D:\\SBU\\Term 2\\AP\\Assignments\\Spotify\\UserDownloads\\" + username + "\\" + fileName;
        File okFile = new File(okFilePath);
        FileOutputStream fileOutputStream = new FileOutputStream(okFile);
        fileOutputStream.write(file_content);
        fileOutputStream.close();
    }

    public String musicAddress(String track_id) throws IOException {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("number", "20");
        jsonObject.put("track_id", track_id);
        String jsonCommand = jsonObject.toString();
        this.output.writeUTF(jsonCommand);
        this.output.flush();
        return this.input.readUTF();
    }

    public boolean checkUserLike(String user_id, String track_id) throws IOException {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("number", "21");
        jsonObject.put("user_id", user_id);
        jsonObject.put("track_id", track_id);
        String jsonCommand = jsonObject.toString();
        this.output.writeUTF(jsonCommand);
        this.output.flush();
        return this.input.readBoolean();
    }

    public void addLike(String user_id, String track_id) throws IOException {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("number", "22");
        jsonObject.put("user_id", user_id);
        jsonObject.put("track_id", track_id);
        String jsonCommand = jsonObject.toString();
        this.output.writeUTF(jsonCommand);
        this.output.flush();
        this.input.readUTF();
    }

    public void addDisLike(String user_id, String track_id) throws IOException {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("number", "23");
        jsonObject.put("user_id", user_id);
        jsonObject.put("track_id", track_id);
        String jsonCommand = jsonObject.toString();
        this.output.writeUTF(jsonCommand);
        this.output.flush();
        this.input.readUTF();
    }

    public void addDownload(String user_id, String track_id) throws IOException {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("number", "24");
        jsonObject.put("user_id", user_id);
        jsonObject.put("track_id", track_id);
        String jsonCommand = jsonObject.toString();
        this.output.writeUTF(jsonCommand);
        this.output.flush();
        this.input.readUTF();
    }

    public boolean checkFollowUser(String user_id_1, String user_id_2) throws IOException {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("number", "25");
        jsonObject.put("user_id_1", user_id_1);
        jsonObject.put("user_id_2", user_id_2);
        String jsonCommand = jsonObject.toString();
        this.output.writeUTF(jsonCommand);
        this.output.flush();
        return this.input.readBoolean();
    }

    public void addFollowUser(String user_id_1, String user_id_2) throws IOException {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("number", "26");
        jsonObject.put("user_id_1", user_id_1);
        jsonObject.put("user_id_2", user_id_2);
        String jsonCommand = jsonObject.toString();
        this.output.writeUTF(jsonCommand);
        this.output.flush();
        this.input.readUTF();
    }

    public int numberOfFollowings_UserToUser(String user_id) throws IOException {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("number", "27");
        jsonObject.put("user_id", user_id);
        String jsonCommand = jsonObject.toString();
        this.output.writeUTF(jsonCommand);
        this.output.flush();
        return this.input.readInt();
    }

    public JSONObject getRow_i_UsernameOfUserToUserFollowings(int n, String user_id) throws IOException {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("number", "28");
        jsonObject.put("row", n);
        jsonObject.put("user_id", user_id);
        String jsonCommand = jsonObject.toString();
        this.output.writeUTF(jsonCommand);
        this.output.flush();
        return new JSONObject(this.input.readUTF());
    }

    public boolean checkFollowArtist(String user_id, String artist_id) throws IOException {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("number", "29");
        jsonObject.put("user_id", user_id);
        jsonObject.put("artist_id", artist_id);
        System.out.println(artist_id);
        String jsonCommand = jsonObject.toString();
        this.output.writeUTF(jsonCommand);
        this.output.flush();
        return this.input.readBoolean();
    }

    public void addFollowArtist(String user_id, String artist_id) throws IOException {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("number", "30");
        jsonObject.put("user_id", user_id);
        jsonObject.put("artist_id", artist_id);
        String jsonCommand = jsonObject.toString();
        this.output.writeUTF(jsonCommand);
        this.output.flush();
        this.input.readUTF();
    }

    public int numberOfFollowings_UserToArtist(String user_id) throws IOException {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("number", "31");
        jsonObject.put("user_id", user_id);
        String jsonCommand = jsonObject.toString();
        this.output.writeUTF(jsonCommand);
        this.output.flush();
        return this.input.readInt();
    }

    public JSONObject getRow_i_nameOfUserToArtistFollowings(int n, String user_id) throws IOException {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("number", "32");
        jsonObject.put("row", n);
        jsonObject.put("user_id", user_id);
        String jsonCommand = jsonObject.toString();
        this.output.writeUTF(jsonCommand);
        this.output.flush();
        return new JSONObject(this.input.readUTF());
    }

    public int numberOfFollowers_UserToUser(String user_id) throws IOException {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("number", "33");
        jsonObject.put("user_id", user_id);
        String jsonCommand = jsonObject.toString();
        this.output.writeUTF(jsonCommand);
        this.output.flush();
        return this.input.readInt();
    }

    public JSONObject getRow_i_usernameOfUserToUserFollowers(int i, String user_id) throws IOException {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("number", "34");
        jsonObject.put("row", i);
        jsonObject.put("user_id", user_id);
        String jsonCommand = jsonObject.toString();
        this.output.writeUTF(jsonCommand);
        this.output.flush();
        return new JSONObject(this.input.readUTF());
    }

    public void unfollow_UserToUser(String user_id_1, String user_id_2) throws IOException {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("number", "35");
        jsonObject.put("user_id_1", user_id_1);
        jsonObject.put("user_id_2", user_id_2);
        String jsonCommand = jsonObject.toString();
        this.output.writeUTF(jsonCommand);
        this.output.flush();
        this.input.readUTF();
    }

    public void unfollow_UserToArtist(String user_id, String artist_id) throws IOException {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("number", "36");
        jsonObject.put("user_id", user_id);
        jsonObject.put("artist_id", artist_id);
        String jsonCommand = jsonObject.toString();
        this.output.writeUTF(jsonCommand);
        this.output.flush();
        this.input.readUTF();
    }

    public int numberOfUserLike(String user_id) throws IOException {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("number", "37");
        jsonObject.put("user_id", user_id);
        String jsonCommand = jsonObject.toString();
        this.output.writeUTF(jsonCommand);
        this.output.flush();
        return this.input.readInt();
    }

    public JSONObject getRow_i_UserLike(int i, String user_id) throws IOException {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("number", "38");
        jsonObject.put("row", i);
        jsonObject.put("user_id", user_id);
        String jsonCommand = jsonObject.toString();
        this.output.writeUTF(jsonCommand);
        this.output.flush();
        return new JSONObject(this.input.readUTF());
    }

    public int numberOfArtistFollowers(String artist_id) throws IOException {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("number", "39");
        jsonObject.put("artist_id", artist_id);
        String jsonCommand = jsonObject.toString();
        this.output.writeUTF(jsonCommand);
        this.output.flush();
        return this.input.readInt();
    }

    public String getRow_i_ArtistFollower(int i, String artist_id) throws IOException {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("number", "40");
        jsonObject.put("row", i);
        jsonObject.put("artist_id", artist_id);
        String jsonCommand = jsonObject.toString();
        this.output.writeUTF(jsonCommand);
        this.output.flush();
        return this.input.readUTF();
    }

    public int numberOfMusicForSpecificArtist(String artist_id) throws IOException {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("number", "41");
        jsonObject.put("artist_id", artist_id);
        String jsonCommand = jsonObject.toString();
        this.output.writeUTF(jsonCommand);
        this.output.flush();
        return this.input.readInt();
    }

    public JSONObject getRow_i_MusicArtist(int i, String artist_id) throws IOException {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("number", "42");
        jsonObject.put("row", i);
        jsonObject.put("artist_id", artist_id);
        String jsonCommand = jsonObject.toString();
        this.output.writeUTF(jsonCommand);
        this.output.flush();
        return new JSONObject(this.input.readUTF());
    }

    public boolean checkPlayListExist(String title) throws IOException {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("number", "43");
        jsonObject.put("title", title);
        String jsonCommand = jsonObject.toString();
        this.output.writeUTF(jsonCommand);
        this.output.flush();
        return this.input.readBoolean();
    }

    public void addPlayList(String playList_id, String user_id, String title) throws IOException {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("number", "44");
        jsonObject.put("playList_id", playList_id);
        jsonObject.put("user_id", user_id);
        jsonObject.put("title", title);
        String jsonCommand = jsonObject.toString();
        this.output.writeUTF(jsonCommand);
        this.output.flush();
        this.input.readUTF();
    }

    public void addMusicToPlayList(String playList_id, String track_id) throws IOException {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("number", "45");
        jsonObject.put("playList_id", playList_id);
        jsonObject.put("track_id", track_id);
        String jsonCommand = jsonObject.toString();
        this.output.writeUTF(jsonCommand);
        this.output.flush();
        this.input.readUTF();
    }

//    public void closeSocket() throws IOException {
//        JSONObject jsonObject = new JSONObject();
//        jsonObject.put("number", "46");
//        jsonObject.put("none", "");
//        String jsonCommand = jsonObject.toString();
//        this.output.writeUTF(jsonCommand);
//        this.output.flush();
//        this.input.readUTF();
//    }

    public void closeDone() throws IOException {
        this.socket.close();
    }
}
