package code;

import org.junit.jupiter.api.Test;
import org.kohsuke.args4j.CmdLineException;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CombiningStringsTest {

    @Test
    void main() throws IOException, CmdLineException {
        CombiningStrings.main("-o files\\output\\output1.txt files\\input\\input1.txt".split(" "));
        assertEquals(
                Files.readString(Path.of("files\\result\\result1.txt")),
                Files.readString(Path.of("files\\output\\output1.txt"))
        );
    }

    @Test
    void ignoreCase() throws IOException, CmdLineException {
        CombiningStrings.main("-i -o files\\output\\output2.txt files\\input\\input2.txt".split(" "));
        assertEquals(
                Files.readString(Path.of("files\\result\\result2.txt")),
                Files.readString(Path.of("files\\output\\output2.txt"))
        );
    }

    @Test
    void ignoreN() throws IOException, CmdLineException {
        CombiningStrings.main("-s 4 -o files\\output\\output3.txt files\\input\\input3.txt".split(" "));
        assertEquals(
                Files.readString(Path.of("files\\result\\result3.txt")),
                Files.readString(Path.of("files\\output\\output3.txt"))
        );
    }

    @Test
    void uniqueStrings() throws IOException, CmdLineException {
        CombiningStrings.main("-u -o files\\output\\output4.txt files\\input\\input1.txt".split(" "));
        assertEquals(
                Files.readString(Path.of("files\\result\\result4.txt")),
                Files.readString(Path.of("files\\output\\output4.txt"))
        );
    }

    @Test
    void numOfStrings() throws IOException, CmdLineException {
        CombiningStrings.main("-c -o files\\output\\output5.txt files\\input\\input1.txt".split(" "));
        assertEquals(
                Files.readString(Path.of("files\\result\\result5.txt")),
                Files.readString(Path.of("files\\output\\output5.txt"))
        );
    }
}