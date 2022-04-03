package ciphore;

import org.kohsuke.args4j.Argument;
import org.kohsuke.args4j.CmdLineException;
import org.kohsuke.args4j.CmdLineParser;
import org.kohsuke.args4j.Option;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class XOR {
    @Option(name = "-c", metaVar = "Encryption")
    private String encKey;

    @Option(name = "-d", metaVar = "Decryption")
    private String decKey;

    @Option(name = "-o", metaVar = "Output file name")
    private String outputFile;

    @Argument(required = true, metaVar = "Input file name")
    private String inputFile;

    public String cipher(String unencryptedFile, String keyInHex) throws IOException {
        String digits = "0123456789ABCDEF";
        keyInHex = keyInHex.toUpperCase();
        int keyInDec = 0;
        for (int i = 0; i < keyInHex.length(); i++) {
            char c = keyInHex.charAt(i);
            int d = digits.indexOf(c);
            keyInDec = 16 * keyInDec + d;
        }

        char[] cipher = unencryptedFile.toCharArray();
        StringBuilder builder = new StringBuilder();
        for (char c : cipher) {
            char ciphered = (char) ((int) c ^ keyInDec);
            builder.append(ciphered);
        }
        return builder.toString();
    }

    private void launch(String[] args) {
        CmdLineParser parser = new CmdLineParser(this);

        try {
            parser.parseArgument(args);
        } catch (CmdLineException e) {
            System.err.println(e.getMessage());
            parser.printUsage(System.err);
        }

       try {
           Path path = Paths.get(inputFile);
           String unencryptedFile = Files.readString(path, StandardCharsets.UTF_8);
           FileOutputStream output = new FileOutputStream(outputFile);
           String res = cipher(unencryptedFile, encKey);
           byte[] buffer = res.getBytes(StandardCharsets.UTF_8);
           output.write(buffer, 0, buffer.length);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args)  {
        System.out.println("Start");
        new XOR().launch(args);
        System.out.println("Stop");
    }
}


