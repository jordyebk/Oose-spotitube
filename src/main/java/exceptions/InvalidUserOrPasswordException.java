package exceptions;

public class InvalidUserOrPasswordException extends Exception {

    public InvalidUserOrPasswordException() {
        super("Invalid username/password.");
    }
}
