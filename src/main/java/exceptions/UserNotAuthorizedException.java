package exceptions;

public class UserNotAuthorizedException extends Exception {
    public UserNotAuthorizedException() {
        super("User with token is not authorized.");
    }
}
