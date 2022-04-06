package softdev.spring.task;

import java.io.*;

import org.apache.commons.io.FileUtils;

import java.util.*;
import static java.util.Map.*;


public class DiskUsage {

    public String totalSizeString; // удобно для тестов
    public float totalSize = 0; // удобно для тестов
    public List<String> fileInfo = new ArrayList<>();// удобно для тестов

    public int reply(boolean h, boolean c, boolean si, boolean order, List<String> Files) throws IOException {

        Map<String, Long> mapFiles = new HashMap<>();

        for (String fileName: Files) {
            File file = new File(fileName);
            long size;
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

        Map<Long, String> swapped = new TreeMap<>();
        if (order) {
            for(Map.Entry<String, Long> fileName : mapFiles.entrySet())
                swapped.put(fileName.getValue(), fileName.getKey());
        }

        if (h && !c) {

            if (order) {
                for (Entry<Long, String> fileName: swapped.entrySet()) {
                    float size = fileName.getKey();
                    int i = 0;
                    while (i < 3 && base <= size) {
                        size /= base;
                        i++;
                    }
                    fileInfo.add(fileName.getValue() + " " + String.format("%.2f", size) + " " + dimension[i]);
                }

            } else {
                for (Map.Entry<String, Long> fileName: mapFiles.entrySet()) {
                    float size = fileName.getValue();
                    int i = 0;
                    while (i < 3 && base <= size) {
                        size /= base;
                        i++;
                    }
                    fileInfo.add(fileName.getKey() + " " + String.format("%.2f", size) + " " + dimension[i]);
                }
            }

            System.out.println(fileInfo);

        } else if (!h && !c) {

            if (order) {
                for (Entry<Long, String> fileName : swapped.entrySet()) {
                    float division = ((float) fileName.getKey() / base);
                    fileInfo.add(fileName.getValue() + " " + String.format("%.2f", division));
                }
            } else {
                for (Map.Entry<String, Long> fileName : mapFiles.entrySet()) {
                    float division = ((float) fileName.getValue()) / base;
                    fileInfo.add(fileName.getKey() + " " + String.format("%.2f", division));
                }
            }
            System.out.println(fileInfo);

        }

        if (c) {
            if (h) {
                int i = 0;
                while (i < 3 && base < totalSize) {
                    totalSize /= base;
                    i++;
                }
                totalSizeString = String.format("%.2f", totalSize) + " " + dimension[i];
            } else {
                totalSizeString = String.format("%.2f", (totalSize / base));
            }
            System.out.println(totalSizeString);
        }
        return 0;
    }

}
