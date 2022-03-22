import org.kohsuke.args4j.Argument;
import org.kohsuke.args4j.CmdLineException;
import org.kohsuke.args4j.CmdLineParser;
import org.kohsuke.args4j.Option;

import java.io.IOException;

public class GrepLauncher {
    @Option(name = "-r", metaVar = "RegexR", required = false, usage = "Regex")
    private boolean regex = false;

    @Option(name = "-v", metaVar = "InversionV", required = false, usage = "Inversion")
    private boolean inversion = false;

    @Option(name = "-i", metaVar = "IgnoreI", required = false, usage = "Ignore")
    private boolean ignore = false;

    @Argument(required = true, metaVar = "Word", usage = "Input word")
    private String word;

    @Argument(required = true, metaVar = "InputName", index = 1, usage = "Input file name")
    private String inputFileName;

    public static void main(String[] args) {
        new GrepLauncher().launch(args);
    }

    private void launch(String[] args) {
        CmdLineParser parser = new CmdLineParser(this);

        try {
            parser.parseArgument(args);
        } catch (CmdLineException e) {
            System.err.println(e.getMessage());
            System.err.println("java -jar grep.jar [-r] [-v] [-i] Word InputName.txt");
            parser.printUsage(System.err);
            return;
        }

        Grep gr = new Grep();
        try {
            gr.grep(regex, ignore, inversion, word, inputFileName);
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }
}
