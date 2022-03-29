package myPackage;

import org.kohsuke.args4j.Argument;
import org.kohsuke.args4j.CmdLineException;
import org.kohsuke.args4j.CmdLineParser;
import org.kohsuke.args4j.Option;

import java.io.File;
import java.io.IOException;

public class LsArgs {
    @Option(name = "-l", metaVar = "long")
    private boolean isLong;
    @Option(name = "-h", metaVar = "human-readable")
    private boolean isHumanReadble;
    @Option(name = "-r", metaVar = "reverse")
    private boolean isReversed;
    @Option(name = "-o", metaVar = "output")
    private File outputFile;
    @Argument(required = true, usage = "Input file name")
    private String directory;

    public LsArgs(String[] args) throws CmdLineException {
        CmdLineParser parser = new CmdLineParser(this);
        parser.parseArgument(args);
    }

    public boolean isLong() {
        return isLong;
    }

    public boolean isHumanReadble() {
        return isHumanReadble;
    }

    public boolean isReversed() {
        return isReversed;
    }

    public File getOutputFile() throws IOException {
        if (outputFile == null) {
            return null;
        }
        outputFile.createNewFile();
        return outputFile;
    }

    public String getDirectory() {
        return directory;
    }
}
