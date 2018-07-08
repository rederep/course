package exception.BD;

public class ChoosingClientEX extends Exception {
    @Override
    public String getMessage() {
        return "Not selected Client";
    }
}
