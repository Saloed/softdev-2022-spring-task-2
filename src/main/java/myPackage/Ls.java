package myPackage;

import org.kohsuke.args4j.CmdLineException;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.FileTime;
import java.text.SimpleDateFormat;
import java.util.*;

public class Ls {
    public static void main(String[] args) throws CmdLineException, IOException {
        LsArgs values = new LsArgs(args);
        File folder = new File(values.getDirectory());
        File[] list;

        if (folder.isFile()) {
            list = new File[]{folder};
        } else {
            list = folder.listFiles();
        }

        if (values.isReversed()) {
            List<File> reversed = Arrays.asList(list);
            Collections.reverse(reversed);
            list = reversed.toArray(list);
        }
        String result = "";
        if (!values.isLong()) {
            result = getNames(list);
        } else {
            if (!values.isHumanReadble()) {
                result = getLongInfo(list);
            } else {
                result = getHumanReadableInfo(list);
            }
        }

        File output = values.getOutputFile();
        if (output != null) {
            FileWriter writer = new FileWriter(output);
            writer.write(result);
            writer.close();
        } else {
            System.out.print(result);
        }
    }

    private static String getNames(File[] list) {
        StringBuilder sb = new StringBuilder();
        for (File file : list) {
            sb.append(file.getName() + System.lineSeparator());
        }
        return sb.toString();
    }

    private static String getLongInfo(File[] list) throws IOException {
        StringBuilder sb = new StringBuilder();
        for (File file : list) {
            if (file.canRead()) sb.append(1);
            else sb.append(0);

            if (file.canWrite()) sb.append(1);
            else sb.append(0);

            if (file.canExecute()) sb.append(1);
            else sb.append(0);

            sb.append(" ");
            Path path = Path.of(file.getAbsolutePath());
            sb.append(Files.size(path));
            sb.append(" ");
            sb.append(Files.getLastModifiedTime(path));
            sb.append(" ");
            sb.append(file.getName() + System.lineSeparator());
        }
        return sb.toString();
    }

    private static String getHumanReadableInfo(File[] list) throws IOException {
        StringBuilder sb = new StringBuilder();
        for (File file : list) {
            if (file.canRead()) sb.append("r");
            else sb.append("-");

            if (file.canWrite()) sb.append("w");
            else sb.append("-");

            if (file.canExecute()) sb.append("x");
            else sb.append("-");

            sb.append(" ");
            Path path = Path.of(file.getAbsolutePath());
            long size = Files.size(path);
            sb.append(sizeForHuman(size));
            sb.append(" ");
            FileTime time = Files.getLastModifiedTime(path);
            sb.append(timeForHuman(time));
            sb.append(" ");
            sb.append(file.getName() + System.lineSeparator());
        }
        return sb.toString();
    }


    private static String timeForHuman(FileTime time) {
        Date date = new Date(time.toMillis());
        String pattern = "d-MM-y";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        return simpleDateFormat.format(date);
    }

    private static String sizeForHuman(long size) {
        String[] namesList = new String[] {"б", "Кб", "Мб", "Гб", "Тб"};
        long multiplier = 1024;
        int index = 0;
        long currentSize = size;
        while (currentSize/multiplier > 0) {
            index++;
            currentSize = currentSize / multiplier;
        }
        if (index >= namesList.length) return "Too big size";
        return currentSize + namesList[index];
    }
}
