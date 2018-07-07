package exception.BD;

public class CannotCloseConnect extends Exception{
    @Override
    public String getMessage() {
        return "Can't close connection.";
    }
}
