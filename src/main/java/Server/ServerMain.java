package Server;

import Shared.ArtistResponse;
import Shared.UserResponse;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.SQLException;

public class ServerMain {
    public static void main(String[] args) throws IOException, SQLException {
        final int SBAP_PORT = 6666;
        ServerSocket serverSocket = new ServerSocket(SBAP_PORT);
        System.out.println("Waiting for client to connect...");
        while (true) {
            Socket myUser = serverSocket.accept();
            DataInputStream input = new DataInputStream(myUser.getInputStream());
            DataOutputStream output = new DataOutputStream(myUser.getOutputStream());
            String position = input.readUTF();
            switch (position) {
                case "1":
                    System.out.println("Artist Connected.");
                    ArtistResponse artistResponse = new ArtistResponse();
                    ServerServiceForArtist serverServiceForArtist = new ServerServiceForArtist(myUser, input,
                            output, artistResponse);
                    Thread artistThread = new Thread(serverServiceForArtist);
                    artistThread.start();
                    break;
                case "2":
                    System.out.println("User Connected.");
                    UserResponse clientResponse = new UserResponse(myUser);
                    ServerServiceForUser serverServiceForUser = new ServerServiceForUser(myUser, input,
                            output, clientResponse);
                    Thread clientThread = new Thread(serverServiceForUser);
                    clientThread.start();
                    break;
            }
        }
    }
}