package softdev.spring.task;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;

import java.util.ArrayList;
import java.util.List;

class DiskUsageTest {

    @Test
    public void Test1() throws IOException {
        //тест показывает правильность определения размера директории

        DiskUsage dU = new DiskUsage();
        boolean c = true;
        boolean h = true;
        boolean si = false;
        boolean order = false;
        List<String> a = new ArrayList<>();
        a.add("src/test/testFiles/img1.jpg");
        a.add("src/test/testFiles/pain.jpg");
        dU.reply(h, c, si, order, a);
        assertEquals("2,02 MB", dU.totalSizeString);

        a.remove("src/test/testFiles/img1.png");
        a.remove("src/test/testFiles/pain.jpg");
        a.add("src/test/testFiles");
        assertEquals("2,02 MB", dU.totalSizeString);
    }

    @Test
    public void Test2() throws IOException {
        DiskUsage dU = new DiskUsage();
        boolean c = true;
        boolean h = true;
        boolean si = true;
        boolean order = false;
        List<String> a = new ArrayList<>();
        a.add("src/test/testFiles/pain.jpg");
        dU.reply(h, c, si, order, a);
        assertEquals("223,07 KB", dU.totalSizeString);
    }

    @Test
    public void Test3() throws IOException {
        DiskUsage dU = new DiskUsage();
        boolean c = false;
        boolean h = true;
        boolean si = false;
        boolean order = true;
        List<String> a = new ArrayList<>();
        a.add("src/test/testFiles/img1.jpg");
        a.add("src/test/testFiles/pain.jpg");
        dU.reply(h, c, si, order, a);
        assertEquals(List.of("pain.jpg 217,84 KB", "img1.jpg 1,81 MB"), dU.fileInfo);
    }

    @Test
    public void Test4() throws IOException {
        DiskUsage dU = new DiskUsage();
        boolean c = true;
        boolean h = false;
        boolean si = false;
        boolean order = false;
        List<String> a = new ArrayList<>();
        a.add("src/test/testFiles/img1.jpg");
        dU.reply(h, c, si, order, a);
        assertEquals("1853,36", dU.totalSizeString);
    }

    @Test
    public void Test5() throws IOException {
        DiskUsage dU = new DiskUsage();
        boolean c = false;
        boolean h = false;
        boolean si = false;
        boolean order = false;
        List<String> a = new ArrayList<>();
        a.add("src/test/testFiles/pain.jpg");
        assertEquals(0, dU.reply(h, c, si, order, a));
    }

    @Test
    public void Test6() throws IOException {
        DiskUsage dU = new DiskUsage();
        boolean c = false;
        boolean h = false;
        boolean si = false;
        boolean order = false;
        List<String> a = new ArrayList<>();
        a.add("src/test/testFiles/monkey.jpg");
        assertEquals(1, dU.reply(h, c, si, order, a));
    }

}
