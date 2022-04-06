package ciphxorTest;

import ciphxor.XOR;
import org.junit.jupiter.api.Test;
import java.io.*;
import static org.junit.jupiter.api.Assertions.assertEquals;


public class XORetest {
    @Test
    public void test() throws IOException {
        String testMessage = "test massage/ проверка";
        byte[] messageInByte = testMessage.getBytes();
        String key = "ff";
        byte[] encoding = new XOR().cipher(messageInByte, key);
        byte[] decoding = new XOR().cipher(encoding, key);
        String encodeTextMessage = new String(decoding);
        assertEquals(testMessage, encodeTextMessage);
    }
}
