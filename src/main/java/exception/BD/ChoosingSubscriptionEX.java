package exception.BD;

public class ChoosingSubscriptionEX extends Exception {
    @Override
    public String getMessage() {
        return "Not selected subscription for subscription";
    }
}
