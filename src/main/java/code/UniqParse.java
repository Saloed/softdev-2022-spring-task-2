package code;

import org.kohsuke.args4j.Argument;
import org.kohsuke.args4j.CmdLineException;
import org.kohsuke.args4j.CmdLineParser;
import org.kohsuke.args4j.Option;

public class UniqParse {

    @Option(name = "-o", metaVar = "outputName")
    private String ofile;

    @Option(name = "-i", metaVar = "ignoreCase")
    private boolean i;

    @Option(name = "-s", metaVar = "ignore")
    private int s;

    @Option(name = "-u", metaVar = "uniqueStrings")
    private boolean u;

    @Option(name = "-c", metaVar = "numOfStrings")
    private boolean c;

    @Argument(metaVar = "inputName", usage = "Name of input file(s)", multiValued = true)
    private String input;

    public UniqParse(String[] args) throws CmdLineException {
        CmdLineParser parser = new CmdLineParser(this);
        try {
            parser.parseArgument(args);
        } catch (CmdLineException e) {
            throw new IllegalArgumentException("ERROR" + e);
        }
    }

    public String inputFileName() {
        return input;
    }

    public String outputFileName() {
        return ofile;
    }

    public boolean ignoreCase() {
        return i;
    }

    public int ignoreN() {
        return s;
    }

    public boolean uniqueStrings() {
        return u;
    }

    public boolean numOfStrings() {
        return c;
    }
}
