package exception;

public class NotDeletedWorkerEX extends Exception {
    @Override
    public String getMessage() {
        return  "Sorry you cant delete Worker because it have dependencies on client Visits";
    }
}
