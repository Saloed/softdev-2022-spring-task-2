import org.kohsuke.args4j.Argument
import org.kohsuke.args4j.CmdLineException
import org.kohsuke.args4j.CmdLineParser
import org.kohsuke.args4j.Option
import java.io.IOException


class GrepLauncher {
    @Option(name = "-r", metaVar = "RegexR", required = false, usage = "Regex")
    private val regex = false

    @Option(name = "-v", metaVar = "InversionV", required = false, usage = "Inversion")
    private val inversion = false

    @Option(name = "-i", metaVar = "IgnoreI", required = false, usage = "Ignore")
    private val ignore = false

    @Argument(required = true, metaVar = "Word", usage = "Input word")
    private val word: String? = null

    @Argument(required = true, metaVar = "InputName", index = 1, usage = "Input file name")
    private val inputFileName: String? = null

    fun launch(args: Array<String>) {
        val parser = CmdLineParser(this)
        try {
            parser.parseArgument(*args)
        } catch (e: CmdLineException) {
            System.err.println(e.message)
            System.err.println("java -jar grep.jar [-r] [-v] [-i] Word InputName.txt")
            parser.printUsage(System.err)
            return
        }
        val gr = Grep()
        try {
            gr.grep(regex, ignore, inversion, word!!, inputFileName!!)
        } catch (e: IOException) {
            System.err.println(e.message)
        }
    }
}

fun main(args: Array<String>) {
    GrepLauncher().launch(args)
}