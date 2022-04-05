package packrle;

import org.kohsuke.args4j.CmdLineException;
import org.kohsuke.args4j.CmdLineParser;
import org.kohsuke.args4j.Option;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Objects;

public class PackRLE {
        @Option(name = "-out")
        private String outputFileName;
        @Option(name = "-z", forbids = {"-u"})
        private String inputFileNameToPack;
        @Option(name = "-u", forbids = {"-z"})
        private String inputFileNameToUnpack;

        public static void main(String[] args) {
                new PackRLE().launch(args);
        }

        private void launch(String[] args) {
                CmdLineParser parser = new CmdLineParser(this);
                try {
                        parser.parseArgument(args);
                } catch (CmdLineException e) {
                        System.err.println(e.getMessage());
                        parser.printUsage(System.err);
                        return;
                }

                try {
                        if (inputFileNameToPack != null)
                                try (FileReader fr = new FileReader(inputFileNameToPack); FileWriter fw = new FileWriter(
                                        Objects.requireNonNullElse(outputFileName, inputFileNameToPack + ".rle"))) {
                                        new Packer().pack(fr, fw);
                                }
                        else if (inputFileNameToUnpack != null)
                                try (FileReader fr = new FileReader(inputFileNameToUnpack); FileWriter fw = new FileWriter(
                                        Objects.requireNonNullElse(outputFileName, inputFileNameToUnpack + ".rle"))) {
                                        new Packer().unPack(fr, fw);
                                }
                        else parser.printUsage(System.err);

                } catch (IOException e) {
                        e.printStackTrace();
                }
        }

}
