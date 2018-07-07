package exception.BD;

public class DriverBDNotFound extends Exception {
    @Override
    public String getMessage() {
        return "Can't get class. No driver found.";
    }
}
