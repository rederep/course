package exception;

public class ModelNotFoundEX extends Exception {
    public ModelNotFoundEX(String s) {
        super("Sory, but we can found model by "+s);
    }
}
