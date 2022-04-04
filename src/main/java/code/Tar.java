package code;

import org.kohsuke.args4j.CmdLineException;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Objects;

public class Tar {
    public static void main(String[] args) throws IllegalArgumentException{
        TarParse arguments = new TarParse(args);
        if (arguments.getUnmerge()!=null && (arguments.getOutput() != null)
                || arguments.getUnmerge()==null && arguments.getOutput() == null)
            throw new IllegalArgumentException("only one flag please");
        if (arguments.getUnmerge() != null) uVariant(arguments.getUnmerge());
        else outVariant(Arrays.copyOfRange(arguments.getInput(), 0, arguments.getInput().length), arguments.getOutput());
    }

    private static void uVariant(String name){
        try {
            File file = new File(name);
            FileReader fr = new FileReader(file);
            BufferedReader reader = new BufferedReader(fr);
            String line = reader.readLine();
            String[] names = line.split("\t");
            FileInputStream inputStream = new FileInputStream(name);
            byte[] head = new byte[line.length()+2];
            inputStream.read(head, 0, line.length() + 2);
            for (String s : names) {
                byte[] bytes = new byte[Integer.parseInt(s.split(" ")[1])+1];
                inputStream.read(bytes, 0, Integer.parseInt(s.split(" ")[1])+1);
                FileOutputStream outputStream = new FileOutputStream(s.split(" ")[0], false);
                outputStream.write(bytes);
                outputStream.close();
            }
            inputStream.close();
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    private static void outVariant(String[] names, String output){
        String heading = "";
        for (String s: names){
            long partSize = new File(s).length();
            heading = heading + s + " " + partSize + "\t";
        }
        heading = heading.substring(0, heading.length()-1);
        heading += "\n";
        byte[] bytes = heading.getBytes();
              try{
            File out = new File(output);
            FileOutputStream outputStream = new FileOutputStream(out, true);
            outputStream.write(bytes);
            for (String s : names){
                File file = new File(s);
                FileInputStream inputStream = new FileInputStream(s);
                outputStream.write(inputStream.readAllBytes());
                inputStream.close();
            }
            outputStream.close();
        }catch (IOException e){
            e.printStackTrace();
        }

    }
}