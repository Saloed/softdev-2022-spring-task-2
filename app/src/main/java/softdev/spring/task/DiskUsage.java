package softdev.spring.task;

import java.io.*;

import org.apache.commons.io.FileUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class DiskUsage {

    public String totalSizeString; // удобно для тестов
    public float totalSize = 0; // удобно для тестов
    public List<String> fileInfo = new ArrayList<>(); // удобно для тестов

    public int reply(boolean h, boolean c, boolean si, List<String> Files) throws IOException {

        Map<String, Long> mapFiles = new HashMap<>();

        for (String fileName: Files) {
            File file = new File(fileName);
            long size = 0;
            if (file.isFile()) {
                size = file.length();
            }
            else if (file.isDirectory()) {
                size = FileUtils.sizeOfDirectory(file);
            } else return 1;
            totalSize += size;
            mapFiles.put(file.getName(), size);
        }

        int base = si ? 1000: 1024;

        String[] dimension = {"B", "KB", "MB", "GB"};

        if (h && !c) {
            for (Map.Entry<String, Long> fileName: mapFiles.entrySet()) {
                float size = fileName.getValue();
                //totalSize += size;
                int i = 0;
                while (i < 3 && base <= size) {
                    size /= base;
                    i++;
                }
                fileName.setValue((long) size);
                fileInfo.add("%s %s %s".formatted(fileName.getKey(), String.format("%.2f", size), dimension[i]));
            }
        } else if (!h && !c) {
            for (Map.Entry<String, Long> fileName: mapFiles.entrySet()) {
                //totalSize += fileName.getValue();
                float size = fileName.getValue();
                float division = size / base;
                fileName.setValue((long) size);
                fileInfo.add("%s %s".formatted(fileName.getKey(), String.format("%.2f", division)));
            }
        }

        if (c) {
            if (h) {
                int i = 0;
                while (i < 3 && base < totalSize) {
                    totalSize /= base;
                    i++;
                }
                totalSizeString = "%s %s".formatted(String.format("%.2f", totalSize), dimension[i]);
            } else {
                totalSizeString = String.format("%.2f", (totalSize / base));
            }
            System.out.println(totalSizeString);
        }

        return 0;
    }

}
