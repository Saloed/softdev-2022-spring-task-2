package ls;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class CommandsList {
    private final List<Commands> files = new ArrayList<>();

    public CommandsList(String way) {
        File dir = new File(way);
        if (dir.isDirectory()) {
            for (File file : Objects.requireNonNull(dir.listFiles())) {
                files.add(new Commands(file));
            }
        } else files.add(new Commands(dir));
    }

    public String toString(Args args) {
        if (files.isEmpty()) return "Директория не содержит ни одного файла";
        if (args.r) Collections.reverse(files);
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < files.size(); i++) {
            result.append(files.get(i).toString(args));
            if (i < files.size() - 1) result.append("\n");
        }
        return result.toString();
    }
}
