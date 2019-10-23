package Exceptions;

public class InsertionError extends Exception {

    public InsertionError(String itemToInsert) {
        super("There was an error attempting to insert into database: " + itemToInsert);
    }
}
