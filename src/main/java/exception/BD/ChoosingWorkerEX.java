package exception.BD;

public class ChoosingWorkerEX extends Exception {
    @Override
    public String getMessage() {
        return "Not selected Worker";
    }
}
