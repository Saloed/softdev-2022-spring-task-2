import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Random;
import packrle.*;

public class PackerTest {

    private void assertFileContext(String name, String expectedContent) throws IOException {
        String content = Files.lines(Paths.get(name)).reduce("", String::concat);
        Assertions.assertEquals(expectedContent, content);
    }

    private String randomString(int min, int max, int maxRepeats, int numberOfChars) {
        StringBuilder sb = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < numberOfChars; i++) {
            char c = (char) (random.nextInt((max - min) + 1) + min);
            int repeat = random.nextInt(maxRepeats + 1);
            sb.append(Character.toString(c).repeat(repeat));
        }
        return sb.toString();
    }

    @Test
    void testPacking() throws IOException {
        Packer packer = new Packer();
        String inputFileName = "files/testPacking.txt";
        String outputFileName = "files/temp.txt";
        packer.pack(new FileReader(inputFileName), new FileWriter(outputFileName));
        assertFileContext("files/temp.txt", "ABC3D10F");
    }

    @Test
    void testUnpacking() throws IOException {
        Packer packer = new Packer();
        String inputFileName = "files/testUnpacking.txt";
        String outputFileName = "files/temp.txt";
        packer.unPack(new FileReader(inputFileName), new FileWriter(outputFileName));
        assertFileContext("files/temp.txt", "ABCDDDFFFFFFFFFF");
    }

    @Test
    void randomTest() throws IOException {
        Packer packer = new Packer();
        for (int i = 0; i < 50; i++) {
            String str = randomString('0', 'Z', 3, 10);
            StringWriter packed = new StringWriter();
            packer.pack(new StringReader(str), packed);
            StringWriter unPacked = new StringWriter();
            packer.unPack(new StringReader(packed.toString()), unPacked);
            Assertions.assertEquals(str, unPacked.toString());
        }
    }
}
