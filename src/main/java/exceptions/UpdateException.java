package exceptions;

public class UpdateException extends Exception {

    public UpdateException(String itemToUpdate) {
        super("There was an error attempting to update in database database: " + itemToUpdate);
    }
}
