package code;


import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.Test;
import org.kohsuke.args4j.CmdLineException;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TarTest {
    @Test
    public void uTest() throws IOException, CmdLineException {
        Tar.main("-u files\\uVariant\\uTest.txt".split(" "));
        File file1 = new File("files\\uVariant\\1.txt");
        File exp1 = new File("files\\uVariant\\expect1.txt");
        File file2 = new File("files\\uVariant\\2.txt");
        File exp2 = new File("files\\uVariant\\expect1.txt");
        FileUtils.contentEquals(file1,exp1);
        FileUtils.contentEquals(file2, exp2);
    }

    @Test
    public void outTest() throws IOException, CmdLineException {
        Tar.main("files\\outVariant\\1.txt files\\outVariant\\2.txt -out files\\outVariant\\3.txt".split(" "));
        File act = new File("files\\outVariant\\3.txt");
        File exp = new File("files\\outVariant\\expect.txt");
        FileUtils.contentEquals(act,exp);

    }
}
