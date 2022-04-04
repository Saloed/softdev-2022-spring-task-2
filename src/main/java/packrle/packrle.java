package packrle;

import org.kohsuke.args4j.CmdLineException;
import org.kohsuke.args4j.CmdLineParser;
import org.kohsuke.args4j.Option;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Objects;

public class packrle {
        @Option(name = "-out")
        private String outputFileName;
        @Option(name = "-z", forbids = {"-u"})
        private String inputFileNameToPack;
        @Option(name = "-u", forbids = {"-z"})
        private String inputFileNameToUnpack;

        public static void main(String[] args) {
                new packrle().launch(args);
        }

        private void launch(String[] args) {
                CmdLineParser parser = new CmdLineParser(this);
                try {
                        parser.parseArgument(args);
                } catch (CmdLineException e) {
                        System.err.println(e.getMessage());
                        parser.printUsage(System.err);
                }

                try {
                        if (inputFileNameToPack != null)
                                new Packer().pack(new FileReader(inputFileNameToPack), new FileWriter(
                                        Objects.requireNonNullElse(outputFileName, inputFileNameToPack + ".rle")));
                        else if (inputFileNameToUnpack != null)
                                new Packer().unPack(new FileReader(inputFileNameToUnpack), new FileWriter(
                                        Objects.requireNonNullElse(outputFileName, inputFileNameToUnpack + ".rle")));
                        else {
                                parser.printUsage(System.err);
                        }
                } catch (IOException e) {
                        e.printStackTrace();
                }
        }

}
