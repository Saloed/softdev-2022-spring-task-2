package ciphxor;
import org.apache.commons.codec.binary.Hex;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.nio.charset.StandardCharsets;
import java.util.Arrays;

public class CiphxorTest {
    @Test
    public void cipher() {
        String input = "Hello, I'm testing Ciphxor.jar!";
        byte[] inputBytes = input.getBytes();
        String key = "ad42bc842febb";
        byte[] encoded = Ciphxor.cipher(inputBytes, key);
        byte[] outputBytes = Ciphxor.cipher(encoded, key);
        String output = new String(outputBytes, StandardCharsets.UTF_8);
        assertEquals(input, output);
    }
}
