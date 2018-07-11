package exception;

public class IllegalArgumentEX extends Exception{
    @Override
    public String getMessage() {
        return "Empty passwords are not supported.";
    }
}
