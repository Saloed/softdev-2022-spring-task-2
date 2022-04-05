package code;

import org.kohsuke.args4j.CmdLineException;

import java.io.*;

public class Tar {
    public static void main(String[] args) throws IllegalArgumentException, CmdLineException {
        TarParse arguments = new TarParse(args);
        if (arguments.getUnmerge() != null) uVariant(arguments.getUnmerge());
        else outVariant(arguments.getInput(), arguments.getOutput());
    }

    private static void uVariant(String name) {
        try (FileReader fr = new FileReader(name);
             BufferedReader reader = new BufferedReader(fr);
             FileInputStream inputStream = new FileInputStream(name)) {
            String line = reader.readLine();
            String[] names = line.split("\\*");
            byte[] head = new byte[line.length() + 2];
            inputStream.read(head, 0, line.length() + 2);
            for (String s : names) {
                byte[] bytes = new byte[Integer.parseInt(s.split("\\?")[1]) + 1];
                inputStream.read(bytes, 0, Integer.parseInt(s.split("\\?")[1]) + 1);
                try (FileOutputStream outputStream = new FileOutputStream(s.split("\\?")[0], false)) {
                    outputStream.write(bytes);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void outVariant(String[] names, String output) {
        StringBuilder heading = new StringBuilder();
        for (String s : names) {
            long partSize = new File(s).length();
            heading.append(s).append("\\?").append(partSize).append("\\*");
        }
        heading = new StringBuilder(heading.substring(0, heading.length() - 1));
        heading.append("\n");
        byte[] bytes = heading.toString().getBytes();
        try (FileOutputStream outputStream = new FileOutputStream(output, true)){
            outputStream.write(bytes);
            for (String s : names) {
                File file = new File(s);
                try(FileInputStream inputStream = new FileInputStream(s)){
                outputStream.write(inputStream.readAllBytes());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}