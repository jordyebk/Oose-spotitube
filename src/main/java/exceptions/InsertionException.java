package exceptions;

public class InsertionException extends Exception {

    public InsertionException(String itemToInsert) {
        super("There was an error attempting to insert into database: " + itemToInsert);
    }
}
