package code;

import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;


public class UniqTest {

    @Test
    public String main() throws IOException, IllegalArgumentException {
        BufferedReader reader = new BufferedReader(new FileReader(".idea/output.txt"));
        String line = null;
        StringBuilder stringBuilder = new StringBuilder();
        String ls = System.getProperty("line.separator");
        while ((line = reader.readLine()) != null) {
            stringBuilder.append(line);
            stringBuilder.append(ls);
        }

        stringBuilder.deleteCharAt(stringBuilder.length() - 1);
        System.out.println(stringBuilder.toString());
        return stringBuilder.toString();
    }
}
