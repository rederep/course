import org.junit.Test;
import service.Password;

import static org.junit.Assert.assertEquals;

public class PasswordTest {
    @Test
    public void passwordCheck(){
        try {
            boolean result = Password.check("123","5WCDh7SLn+oY1PJqnKfL3yfi9EyPZOdyKHRQFh80HmI=$pJKsxaV+ExQ8+cMHzDLqxL/GxMppJZq4sy6pGZ4qR6c=");
            assertEquals(true,result);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
