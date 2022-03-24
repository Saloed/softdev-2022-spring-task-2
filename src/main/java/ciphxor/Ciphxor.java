package ciphxor;

import org.apache.commons.codec.binary.Hex;
import org.codehaus.plexus.util.IOUtil;
import org.kohsuke.args4j.Argument;
import org.kohsuke.args4j.CmdLineException;
import org.kohsuke.args4j.CmdLineParser;
import org.kohsuke.args4j.Option;

import java.io.*;

public class Ciphxor {
    @Option(name = "-c", forbids = "-d")
    private String encryptionKey;
    @Option(name = "-d", forbids = "-c")
    private String decryptionKey;
    @Option(name = "-o")
    private String outputFileName;
    @Argument(required = true)
    private String inputFileName;

    public static void main(String[] args) {
        new Ciphxor().launch(args);
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
            if (outputFileName.isEmpty()) outputFileName = inputFileName + ".ciph";
            try (FileInputStream inputStream = new FileInputStream(inputFileName);
                 FileOutputStream outputStream = new FileOutputStream(outputFileName);
                 OutputStreamWriter writer = new OutputStreamWriter(outputStream)) {
                String output;
                if (encryptionKey == null) {
                    String inputHex = IOUtil.toString(inputStream);
                    output = decipher(hexToByte(inputHex), decryptionKey);
                }
                else {
                    byte[] input = inputStream.readAllBytes();
                    byte[] byteOutput = cipher(input, encryptionKey);
                    output = Hex.encodeHexString(byteOutput);
                }
                inputStream.close();
                System.out.println(output);
                writer.write(output);
            }
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

    public static byte[] hexToByte(String hex) {
        int len;
        if (hex.length() % 2 == 0) len = hex.length();
        else len = hex.length() - 1;
        byte[] result = new byte[len / 2];
        for (int i = 0; i < len; i += 2) {
            result[i / 2] = (byte) ((Character.digit(hex.charAt(i), 16) << 4) +
                    Character.digit(hex.charAt(i + 1), 16));
        }
        return result;
    }

    public static byte[] cipher(byte[] val, String stringKey) {
        byte[] key = hexToByte(stringKey);
        byte[] result = new byte[val.length];
        int counter = 0;
        for (int i = 0; i < val.length; i++) {
            if (counter == key.length) counter = 0;
            result[i] = (byte) (val[i] ^ key[counter]);
            counter += 1;
        }
        return result;
    }

    public static String decipher(byte[] val, String stringKey) {
        StringBuilder result = new StringBuilder();
        byte[] key = hexToByte(stringKey);
        int counter = 0;
        for (byte aByte : val) {
            if (counter == key.length) counter = 0;
            result.append((char) (aByte ^ key[counter]));
            counter += 1;
        }
        return result.toString();
    }
}

