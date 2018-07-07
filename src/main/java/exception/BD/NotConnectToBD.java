package exception.BD;

public class NotConnectToBD extends Exception {
    @Override
    public String getMessage() {
        return "Can't get connection. Incorrect URL or password or login.";
    }
}
