package exceptions;

public class TrackException extends Exception {

    public TrackException() {
        super("There has been an error getting tracks.");
    }
}
