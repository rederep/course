package exception;

public class ModelNotFoundEX extends Exception {
    public ModelNotFoundEX(String s) {
        System.out.println("Sorry, but we can found model by "+s);
    }

    @Override
    public String getMessage() {
        return "Sorry, but we can found model";
    }
}
