# Spotify Java Desktop Application

## Overview

This project is a full-featured Spotify-like desktop application built in Java using JavaFX for the UI and MySQL for persistent storage. It supports user authentication, music streaming, playlist management, artist following, and more, with a robust client-server architecture.

## Features

- **User Authentication:** Secure login and signup with password and email validation.
- **Music Library:** Browse, search, and filter music by title, genre, album, and artist.
- **Playlists:** Create, view, and manage personal playlists. Add or remove tracks.
- **Music Player:** Play, pause, reset, and navigate tracks with a custom player UI.
- **Download & Like:** Download tracks and like/dislike music.
- **Artist & User Following:** Follow/unfollow artists and other users.
- **Lyrics Display:** View lyrics for the currently playing track.
- **Responsive UI:** Built with JavaFX, supporting dynamic updates and filtering.
- **Server-Client Architecture:** All data operations are handled via TCP sockets and JSON-based messaging.

## Architecture

- **Client:** JavaFX application (`UI` package) interacts with the user and communicates with the server via the `Shared.UserRequest` class.
- **Server:** Handles requests using `ServerServiceForUser` and `Shared.UserResponse`, connecting to a MySQL database.
- **Data Model:** Classes for `User`, `Music`, `PlayList`, etc., encapsulate domain logic.
- **Database:** MySQL schema with tables for users, music, playlists, likes, downloads, and follows.

## Technologies

- **Java 11+**
- **JavaFX**
- **MySQL**
- **Socket Programming (TCP)**
- **JSON (org.json)**
- **Maven/Gradle** (recommended for dependency management)

## Getting Started

### Prerequisites

- Java 11 or higher
- MySQL server running with the provided schema
- Maven or Gradle (optional, for dependency management)

### Setup

1. **Clone the repository:**

   ```bash
   git clone https://github.com/BardiaAkbari/Spotify
   cd Spotify
   ```

2. **Configure Database:**

   - Create a MySQL database named `spotify`.
   - Import the schema and sample data as needed.
   - Update DB credentials in `UserResponse.java` if necessary.

3. **Build & Run Server:**

   - Compile and run `ServerMain.java` to start the server on port 6666.

4. **Build & Run Client:**
   - Compile and run the JavaFX client application.
   - Ensure the client connects to the correct server IP and port.

### Usage

- **Login/Signup:** Start the client, create an account or log in.
- **Browse Music:** Use the search and filter features to find tracks.
- **Play Music:** Select a track or playlist and use the player controls.
- **Manage Playlists:** Create, edit, and view your playlists.
- **Download & Like:** Download tracks and like/dislike them.
- **Follow Artists/Users:** Discover and follow your favorite artists and users.

## Code Structure

```
src/
  main/
    java/
      Artist/
        Music.java
        PlayList.java
      Shared/
        UserRequest.java
        UserResponse.java
      UI/
        ControllerScene1_1.java
        ControllerSearchMusic_7.java
        ControllerMyPlaylists_14.java
        ControllerViewedPlaylist_15.java
        ControllerMusicPlayer_18.java
        ControllerPlaylistPlayer_19.java
      User/
        User.java
      Server/
        ServerMain.java
        ServerServiceForUser.java
      Test/
        Test_1.java
```

## Contribution

Contributions are welcome! Please fork the repository, create a feature branch, and submit a pull request.

## License

MIT License

## Contact

For questions or support, contact [bardiaa830@gmail.com].
