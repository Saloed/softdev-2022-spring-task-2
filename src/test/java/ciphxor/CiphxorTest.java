package ciphxor;
import org.apache.commons.codec.binary.Hex;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.Arrays;

public class CiphxorTest {
    @Test
    public void cipher() {
        String input = "Hello, I'm testing Ciphxor.jar!";
        byte[] inputBytes = input.getBytes();
        String key = "ad42bc842febb";
        byte[] encoded = Ciphxor.cipher(inputBytes, key);
        System.out.println(Hex.encodeHexString(encoded));
        String output = Ciphxor.decipher(encoded, key);
        assertEquals(input, output);
    }
}
