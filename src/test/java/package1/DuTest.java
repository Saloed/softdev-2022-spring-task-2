package package1;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

public  class DuTest {
    @Test
    public void Test1(){
        final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        Du.main("-h res\\input.txt res\\input1".split(" "));
        assertEquals("input.txt 23,00 B\ninput1 63,00 B".replace("\r",""), outContent.toString().trim().replace("\r",""));
    }

    @Test
    public void Test2(){
        final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        Du.main("-h -c res\\input.txt res\\input1".split(" "));
        assertEquals("86,00 B".replace("\r",""), outContent.toString().trim().replace("\r",""));
    }

    @Test
    public void Test3(){
        final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        Du.main("res\\input.txt res\\input1 res\\d23.jpg".split(" "));
        assertEquals("input.txt 0,02\ninput1 0,06\nd23.jpg 216,42".replace("\r",""), outContent.toString().trim().replace("\r",""));
    }
    @Test
    public void Test4(){
        final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        Du.main("--si res\\input.txt res\\input1 res\\d23.jpg".split(" "));
        assertEquals("input.txt 0,02\ninput1 0,06\nd23.jpg 221,62".replace("\r",""), outContent.toString().trim().replace("\r",""));
    }
}