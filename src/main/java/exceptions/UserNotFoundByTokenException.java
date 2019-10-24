package exceptions;

public class UserNotFoundByTokenException extends Exception {

    public UserNotFoundByTokenException() {
        super("User was not found with given token.");
    }
}
