package Exceptions;

public class DeletionException extends Exception {

    public DeletionException(String itemToDelete) {
        super("There was an error attempting to delete from database: " + itemToDelete);
    }
}
