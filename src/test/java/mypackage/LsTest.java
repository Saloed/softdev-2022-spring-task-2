package mypackage;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.kohsuke.args4j.CmdLineException;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;

import static org.junit.jupiter.api.Assertions.*;

class LsTest {
    private File getFileFromResourses(String fileName) throws URISyntaxException {
        URL url = getClass().getClassLoader().getResource(fileName);
        return new File(url.toURI());
    }

    private String getTextFromFile(File file) throws IOException {
        return Files.readString(file.toPath());
    }
    File output;
    File dir;

    @BeforeEach
    private void before() throws URISyntaxException {
        output = getFileFromResourses("output.txt");
        dir =  getFileFromResourses("testdir");
        System.out.println("working before");
    }

    @Test
    void simpleLsTest() throws IOException, CmdLineException, URISyntaxException {
        String[] args1 = {"-o", output.getAbsolutePath(), dir.getAbsolutePath()};
        Ls.main(args1);

        assertEquals("h.txt\r\ninput.txt", getTextFromFile(output).trim());


        File file =  getFileFromResourses("testdir/h.txt");
        String[] args2 = {"-o", output.getAbsolutePath(), file.getAbsolutePath()};
        Ls.main(args2);

        assertEquals("h.txt", getTextFromFile(output).trim());
    }
}
