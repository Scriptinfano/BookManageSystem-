import javax.swing.*;
import java.sql.Timestamp;

public class Test {
    public static void main(String[] args) {
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        String timestampString = timestamp.toString();
        System.out.println("Current timestamp (string): " + timestampString);



    }
}
