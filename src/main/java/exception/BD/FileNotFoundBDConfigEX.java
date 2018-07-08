package exception.BD;

public class FileNotFoundBDConfigEX extends Exception {
    @Override
    public String getMessage() {
        return "Sorry, But we have no configure BD File";
    }
}
