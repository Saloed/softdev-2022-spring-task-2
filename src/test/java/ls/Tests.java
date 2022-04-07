package ls;

import ls.Args;
import ls.Main;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.nio.file.Files;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Tests {
    public void test(boolean l, boolean h, boolean r, String expected) throws IOException {
        String dir = "files";
        String o = "output.txt";
        Args args = new Args(l, h, r, o, dir);
        Main.start(args);
        File output = new File(args.o);
        assertEquals(Files.readString(output.toPath()), expected);
        output.delete();
    }

    @Test
    public void testL() throws IOException {
        test(true, false, false,
                "testFile1.txt 111 46 07/04/2022 19:50:03\n" +
                        "testFile2.txt 111 10850 07/04/2022 19:53:22\n" +
                        "testFile3.txt 111 0 07/04/2022 19:49:28");
    }

    @Test
    public void testH() throws IOException {
        test(true, true, false,
                "testFile1.txt rwx 46B 07/04/2022 19:50:03\n" +
                        "testFile2.txt rwx 10,6KB 07/04/2022 19:53:22\n" +
                        "testFile3.txt rwx 0B 07/04/2022 19:49:28");
    }

    @Test
    public void testR() throws IOException {
        test(false, false, true,
                "testFile3.txt\n" +
                        "testFile2.txt\n" +
                        "testFile1.txt");
    }

    @Test
    public void testWithoutArguments() throws IOException {
        test(false, false, false,
                "testFile1.txt\n" +
                        "testFile2.txt\n" +
                        "testFile3.txt");
    }

    @Test
    public void testLR() throws IOException {
        test(true, false, true,
                "testFile3.txt 111 0 07/04/2022 19:49:28\n" +
                        "testFile2.txt 111 10850 07/04/2022 19:53:22\n" +
                        "testFile1.txt 111 46 07/04/2022 19:50:03");
    }
}

