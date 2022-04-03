package ciphore;

import org.junit.jupiter.api.Test;
import java.io.*;
import static org.junit.jupiter.api.Assertions.assertEquals;


public class XORetest {
    @Test
    public void test() throws IOException {
        String testMessage = "test massage/ проверка";
        String key = "ff";
        String encoding = new XOR().cipher(testMessage, key);
        String decoding = new XOR().cipher(encoding, key);
        assertEquals(testMessage, decoding);
    }
}
