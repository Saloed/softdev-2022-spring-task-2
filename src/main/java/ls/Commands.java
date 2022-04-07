package ls;

import java.io.File;
import java.text.SimpleDateFormat;

public class Commands {
    private final File file;
    private final String name;

    public String toString(Args args) {
        String result = name;
        if (args.l && !args.h) result += toLongFormat();
        if (args.l && args.h) result += toHumanReadable();
        return result;
    }

    public Commands(File fl) {
        file = fl;
        name = file.getName();
    }

    public String toLongFormat() {
        String result = " ";
        if (file.canRead()) result += 1;
        else result += 0;
        if (file.canWrite()) result += 1;
        else result += 0;
        if (file.canExecute()) result += 1;
        else result += 0;

        result += " " + file.length();

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        result += " " + sdf.format(file.lastModified());

        return result;
    }

    public String toHumanReadable() {
        String result = " ";
        if (file.canRead()) result += "r";
        else result += "-";
        if (file.canWrite()) result += "w";
        else result += "-";
        if (file.canExecute()) result += "x ";
        else result += "- ";

        double longSize = (double) file.length();
        String metricUnits = "B";
        while (longSize / 1024 > 1) {
            longSize /= 1024;
            switch (metricUnits) {
                case "B" -> metricUnits = "KB";
                case "KB" -> metricUnits = "MB";
                case "MB" -> metricUnits = "GB";
            }
        }

        if (metricUnits.equals("B")) result += String.format("%.0f", longSize);
        else result += String.format("%.1f", longSize);
        result += metricUnits;

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        result += " " + sdf.format(file.lastModified());

        return result;
    }
}
