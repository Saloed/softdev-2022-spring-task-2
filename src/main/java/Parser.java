import org.kohsuke.args4j.Argument;
import org.kohsuke.args4j.CmdLineException;
import org.kohsuke.args4j.CmdLineParser;
import org.kohsuke.args4j.Option;

import java.util.ArrayList;
import java.util.List;

public class Parser {
    @Option(name = "-u", usage = "divide the file into 2 parts", forbids = {"-out"})
     String u;
    @Option(name = "-out", usage = "Combining 2 files into 1", forbids = {"-u"})
     String out;
    @Argument
     List<String> arguments = new ArrayList<>();

    public void Pars(String[] args) throws CmdLineException {
        CmdLineParser parser = new CmdLineParser(this);
        parser.parseArgument(args);

    }

}
