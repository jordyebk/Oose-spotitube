package Exceptions;

public class TokenSaveFailedException extends Exception {

    public TokenSaveFailedException() {
        super("Failed to save token.");
    }
}
