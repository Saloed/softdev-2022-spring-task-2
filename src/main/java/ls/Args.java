package ls;

import org.kohsuke.args4j.Argument;
import org.kohsuke.args4j.CmdLineException;
import org.kohsuke.args4j.CmdLineParser;
import org.kohsuke.args4j.Option;


class Args {
    @Option(name = "-l", usage = "long format")
    boolean l;

    @Option(name = "-h", usage = "human readable format")
    boolean h;

    @Option(name = "-r", usage = "reverse")
    boolean r;

    @Option(name = "-o", usage = "output)")
    String o;

    @Argument(metaVar = "dir")
    String dir;

    Args(String[] args) throws CmdLineException {
        CmdLineParser parser = new CmdLineParser(this);
        parser.parseArgument(args);
        if (dir == null) {
            dir = System.getProperty("user.dir");
        }
    }

    Args(boolean l, boolean h, boolean r, String o, String dir) {
        this.l = l;
        this.h = h;
        this.r = r;
        this.o = o;
        this.dir = dir;
    }
}