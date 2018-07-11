package exception;

public class DeletedGood extends  Exception{
    @Override
    public String getMessage() {
        return "\nDeleted was Successful";
    }
}
