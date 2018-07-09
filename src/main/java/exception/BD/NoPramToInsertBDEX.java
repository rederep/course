package exception.BD;

public class NoPramToInsertBDEX extends Exception {
    @Override
    public String getMessage() {
        return "Have not some params to insert in BD";
    }
}
