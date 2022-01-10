package exceptions;

// Exception for invalid entry in console input
public class InvalidEntryException extends Exception {
    public InvalidEntryException (String s){
        super(s);
    }
}
