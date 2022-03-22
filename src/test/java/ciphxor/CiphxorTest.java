package ciphxor;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import ciphxor.Ciphxor.*;

public class CiphxorTest {
    @Test
    public void cipher() {
        String input = "Hello, I'm testing Ciphxor.jar!";
        String key = "ad42bc842febb";
        String encoded = Ciphxor.cipher(input, key);
        assertNotSame(input, encoded);
        System.out.println(encoded);
        String output = Ciphxor.decipher(encoded, key);
        assertEquals(input, output);
    }
}
