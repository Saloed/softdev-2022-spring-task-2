package softdev.spring.task;

import org.kohsuke.args4j.Argument;
import org.kohsuke.args4j.Option;
import org.kohsuke.args4j.CmdLineParser;
import org.kohsuke.args4j.CmdLineException;

import java.io.IOException;

import java.util.List;

public class DiskUsageLauncher {
    @Option(name = "-h", usage = "human-readable format")
    private boolean h;

    @Option(name = "-c", usage = "summary size")
    private boolean c;

    @Option(name = "--si", usage = "base 1000 instead of 1024")
    private boolean si;

    @Option(name = "--order", usage = "sort in ascending order")
    private boolean order;

    @Argument(required = true)
    private List<String> listFiles;

    public static void main(String[] args) {
        new DiskUsageLauncher().launch(args);
    }

    private void launch(String[] args) {
        CmdLineParser parser = new CmdLineParser(this);

        try {
            parser.parseArgument(args);
        } catch (CmdLineException e) {
            System.err.println(e.getMessage());
            System.err.println("java -jar app.jar -h -c --si File1 File2 File3...");
            parser.printUsage(System.err);
            System.exit(1);
            return;
        }

        DiskUsage dU = new DiskUsage();
        try {
            dU.reply(h, c, si, order, listFiles);
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

}
