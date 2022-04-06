package mypackage;

import org.kohsuke.args4j.CmdLineException;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.FileTime;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.List;

public class Ls {

    public static void main(String[] args) throws IOException {
        LsArgs values = null;
        try {
            values = new LsArgs(args);
        } catch (CmdLineException e) {
            System.err.println("Incorrect args");
        }

        if (values != null) {
            argsHandling(values);
        }
    }

    private static void argsHandling(LsArgs values) throws IOException {
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
            FileInfo[] infoList = new FileInfo[list.length];
            for (int i = 0; i < list.length; i++) {
                boolean isReadable = list[i].canRead();
                boolean isWriteable = list[i].canWrite();
                boolean isExecutable = list[i].canExecute();

                String name = list[i].getName();
                Path path = Path.of(list[i].getAbsolutePath());
                long size = Files.size(path);
                FileTime lastModifiedTime = Files.getLastModifiedTime(path);

                infoList[i] = new FileInfo(isReadable,
                        isWriteable,
                        isExecutable,
                        name,
                        size,
                        lastModifiedTime);
            }
            if (!values.isHumanReadable()) {
                //result = getLongInfo(infoList);
                StringBuilder sb = new StringBuilder();
                for (FileInfo fileInfo : infoList) {
                    sb.append(fileInfo.getLong() + System.lineSeparator());
                }
                result = sb.toString();
            } else {
                //result = getHumanReadableInfo(infoList);
                StringBuilder sb = new StringBuilder();
                for (FileInfo fileInfo : infoList) {
                    sb.append(fileInfo.getHumanReadable() + System.lineSeparator());
                }
                result = sb.toString();
            }
        }

        File output = values.getOutputFile();
        if (output != null) {
            try (FileWriter writer = new FileWriter(output)) {
                writer.write(result);
            }
        } else {
            System.out.print(result);
        }
    }

    private static String getNames(File[] list) {
        StringBuilder sb = new StringBuilder();
        for (File file : list) sb.append(file.getName() + System.lineSeparator());
        return sb.toString();
    }

    private static class FileInfo {
        private boolean isReadable;
        private boolean isWriteable;
        private boolean isExecutable;

        private String name;
        private long size;
        private FileTime lastModifiedTime;

        public FileInfo(boolean isReadable,
                        boolean isWriteable,
                        boolean isExecutable,
                        String name,
                        long size,
                        FileTime lastModifiedTime) {
            this.isExecutable = isExecutable;
            this.isReadable = isReadable;
            this.isWriteable = isWriteable;

            this.name = name;
            this.size = size;
            this.lastModifiedTime = lastModifiedTime;
        }

        private String sizeForHuman() {
            String[] namesList = new String[]{"б", "Кб", "Мб", "Гб", "Тб"};
            long multiplier = 1024;
            int index = 0;
            long currentSize = size;
            while (currentSize / multiplier > 0) {
                index++;
                currentSize = currentSize / multiplier;
            }
            if (index >= namesList.length) return "Too big size";
            return currentSize + namesList[index];
        }

        private String timeForHuman() {
            Date date = new Date(lastModifiedTime.toMillis());
            String pattern = "d-MM-y";
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
            return simpleDateFormat.format(date);
        }

        public String getHumanReadable() {
            StringBuilder sb = new StringBuilder();
            if (isReadable) sb.append("r");
            else sb.append("-");

            if (isWriteable) sb.append("w");
            else sb.append("-");

            if (isExecutable) sb.append("x");
            else sb.append("-");

            sb.append(" ");
            sb.append(sizeForHuman());
            sb.append(" ");
            sb.append(timeForHuman());
            sb.append(" ");
            sb.append(name);
            return sb.toString();
        }

        private String getLong() throws IOException {
            StringBuilder sb = new StringBuilder();
            if (isReadable) sb.append(1);
            else sb.append(0);

            if (isWriteable) sb.append(1);
            else sb.append(0);

            if (isExecutable) sb.append(1);
            else sb.append(0);

            sb.append(" ");
            sb.append(size);
            sb.append(" ");
            sb.append(lastModifiedTime);
            sb.append(" ");
            sb.append(name);
            return sb.toString();
        }
    }
}
