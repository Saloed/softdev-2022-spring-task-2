package code;

import org.kohsuke.args4j.Argument;
import org.kohsuke.args4j.CmdLineException;
import org.kohsuke.args4j.CmdLineParser;
import org.kohsuke.args4j.Option;

public class TarParse {
    @Argument(metaVar = "inputName", usage = "Name of input file(s)", multiValued = true)
    private String[] input;
    @Option(name = "-out", metaVar = "merge")
    private String output;
    @Option(name = "-u", metaVar = "unmerge")
    private String unmerge;

    public TarParse(String[] args){
        CmdLineParser parser = new CmdLineParser(this);
        try{
            parser.parseArgument(args);
        } catch (CmdLineException e){
            System.err.println(e.getMessage());
            parser.printUsage(System.err);
        }
    }
    public String[] getInput(){
        return input;
    }

    public String getOutput(){
        return output;
    }

    public String getUnmerge(){
        return unmerge;
    }
}