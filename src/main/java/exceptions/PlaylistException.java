package exceptions;

public class PlaylistException extends Exception {

    public PlaylistException() {
        super("There was an error when loading the playlists.");
    }
}
