package ciphxor;

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
            String key;
            if (outputFileName.isEmpty()) outputFileName = inputFileName + ".ciph";
            try (FileInputStream inputStream = new FileInputStream(inputFileName)) {
                try (FileOutputStream outputStream = new FileOutputStream(outputFileName)) {
                    try (InputStreamReader reader = new InputStreamReader(inputStream)) {
                        try (OutputStreamWriter writer = new OutputStreamWriter(outputStream)) {
                            int str = reader.read();
                            StringBuilder input = new StringBuilder();
                            while (str != -1) {
                                input.append((char) str);
                                str = reader.read();
                            }
                            System.out.println(input);
                            String output;
                            if (encryptionKey != null) output = cipher(input.toString(), encryptionKey);
                            else output = decipher(input.toString(), decryptionKey);
                            System.out.println(output);
                            writer.write(output);
                        }
                    }
                }
            }
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

    public static String cipher(String val, String key) {
        StringBuilder result = new StringBuilder();
        int counter = 0;
        int max = key.length() + 1 - (key.length() + 1) % 4;
        for (int i = 0; i < val.length(); i++) {
            if (counter + 3 >= max) counter = 0;
            String part = key.substring(counter, counter + 3);
            int k = ((int) val.charAt(i) ^ Integer.parseInt(part, 16));
            StringBuilder c = new StringBuilder(Integer.toHexString(k));
            while (c.length() != 4) c.append("0");
            result.append(c);
            counter += 4;
        }
        return result.toString();
    }

    public static String decipher(String val, String key) {
        StringBuilder result = new StringBuilder();
        int counter = 0;
        int max = key.length() + 1 - (key.length() + 1) % 4;
        for (int i = 0; i < val.length(); i += 4) {
            if (counter + 3 >= max) counter = 0;
            String part = key.substring(counter, counter + 3);
            char k = (char) (Integer.parseInt(val.substring(i, i + 3), 16) ^ Integer.parseInt(part, 16));
            result.append(k);
            counter += 4;
        }
        return result.toString();
    }
}

