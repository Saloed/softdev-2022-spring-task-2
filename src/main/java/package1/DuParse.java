package package1;

import org.kohsuke.args4j.Argument;
import org.kohsuke.args4j.CmdLineException;
import org.kohsuke.args4j.CmdLineParser;
import org.kohsuke.args4j.Option;

public class DuParse {
    @Option(name = "-h", usage = "human-readable format")
    private boolean h;

    @Option(name = "-c", usage = "summary size")
    private boolean c;

    @Option(name = "--si", usage = "base 1000 instead of 1024")
    private boolean si;

    @Argument(required = true, usage = "file names")
    private String[] listFiles;

    public DuParse(String[] args) {
        CmdLineParser parser = new CmdLineParser(this);
        try{
            parser.parseArgument(args);
        } catch (CmdLineException e){
            System.err.println(e.getMessage());
            parser.printUsage(System.err);
        }
    }

    public boolean isH(){
        return h;
    }
    public boolean isC(){
        return c;
    }
    public boolean isSi() {return si;}
    public String[] getListFiles(){
        return listFiles;
    }
}
