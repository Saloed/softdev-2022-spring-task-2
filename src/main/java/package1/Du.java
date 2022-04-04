package package1;


import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Du {
    public static void main(String[] args) {
        String[] dimension = {"B", "KB", "MB", "GB"};
        DuParse arguments = new DuParse(args);
        List<String> result = new ArrayList<>();
        Map<String, Long> mapFiles = new HashMap<>();
        int base = 1024;
        if (arguments.isSi()) base = 1000;
        for (String s : arguments.getListFiles()) {
            File file = new File(s);
            if (file.isFile()) {
                mapFiles.put(file.getName(), file.length());
            } else if (file.isDirectory()) {
                mapFiles.put(file.getName(), directorySize(file));
            }
        }
        if (arguments.isC()) {
            long superSize = 0;
            for (Map.Entry<String, Long> entry : mapFiles.entrySet()) {
                superSize += entry.getValue();
            }
            float allSize = superSize;
            if (arguments.isH()) {
                int i = 0;
                while (i < 3 && base <= allSize) {
                    allSize /= base;
                    i++;
                }
                System.out.print(String.format("%.2f", allSize) + " " + dimension[i]);
            }else{
                System.out.printf("%.2f", allSize);
            }
            return;
        }
        if (arguments.isH()) {
            for (Map.Entry<String, Long> entry : mapFiles.entrySet()) {
                float size = entry.getValue();
                int i = 0;
                while (i < 3 && base <= size) {
                    size /= base;
                    i++;
                }
                result.add("%s %s %s".formatted(entry.getKey(), String.format("%.2f", size), dimension[i]));
            }
        }else{
            for (Map.Entry<String, Long> entry : mapFiles.entrySet()) {
                System.out.println(entry.getKey() + " " + String.format("%.2f",Float.valueOf(entry.getValue())/base));
            }
        }
        for (String el : result) System.out.println(el);

    }

    private static Long directorySize(File dir) {
        long size = 0;
        for (File file : dir.listFiles()) {
            size += file.length();
        }
        return size;
    }

}
