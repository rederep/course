package exception;

public class FileNotFoundBDConfigAdm extends Exception {
    @Override
    public String getMessage() {
        return "Sorry, But we have no configure Admin File";
    }
}
