package ciphxor;

import org.kohsuke.args4j.Argument;
import org.kohsuke.args4j.CmdLineException;
import org.kohsuke.args4j.CmdLineParser;
import org.kohsuke.args4j.Option;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class XOR {
    @Option(name = "-c", metaVar = "Encryption")
    private String encKey;

    @Option(name = "-d", metaVar = "Decryption")
    private String decKey;

    @Option(name = "-o", metaVar = "Output file name")
    private String outputFile;

    @Argument(required = true, metaVar = "Input file name")
    private String inputFile;

    public byte[] cipher(byte[] unencryptedFile, String keyInHex) {
        String digits = "0123456789ABCDEF";
        keyInHex = keyInHex.toUpperCase();
        int keyInDec = 0;
        for (int i = 0; i < keyInHex.length(); i++) {
            char c = keyInHex.charAt(i);
            int d = digits.indexOf(c);
            keyInDec = 16 * keyInDec + d;
        }

        byte[] newEncode = new byte[unencryptedFile.length];
        for (int i = 0; i < unencryptedFile.length; i++) {
            newEncode[i] = (byte) (unencryptedFile[i] ^ keyInDec);
        }
        return newEncode;
    }

    private void launch(String[] args) {
        CmdLineParser parser = new CmdLineParser(this);

        try {
            parser.parseArgument(args);
        } catch (CmdLineException e) {
            System.err.println(e.getMessage());
            parser.printUsage(System.err);
            return;
        }

        if(encKey != null && decKey != null) return;

        try {
           FileInputStream input = new FileInputStream(inputFile);
           byte[] unencryptedFile = input.readAllBytes();
           FileOutputStream output = new FileOutputStream(outputFile);
           byte[] res = cipher(unencryptedFile, encKey);
           output.write(res);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args)  {
        new XOR().launch(args);
    }
}


