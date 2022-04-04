import org.kohsuke.args4j.*
import java.io.File

class LsLauncher {
    @Option(name = "-l", aliases = ["--long"], metaVar = "LongFlag", usage = "Additional information")
    private val lFlag = false

    @Option(name = "-h", aliases = ["--human-readable"], metaVar = "HumanFlag", usage = "Human-readable format")
    private val hFlag = false

    @Option(name = "-r", aliases = ["--reverse"], metaVar = "ReverseFlag", usage = "Output order as reversed")
    private val rFlag = false

    @Option(name = "-o", aliases = ["--output"], metaVar = "OutputFlag", usage = "Name of result file")
    private val outputFile: String? = null

    @Argument(required = true, metaVar = "FileOrDir", usage = "Name of the file or directory")
    private val fileOrDir: String = ""

    fun launch(args: Array<String>) {
        val parser = CmdLineParser(this)
        try {
            parser.parseArgument(*args)
        } catch (e: CmdLineException) {
            System.err.println(e.message)
            System.err.println("java -jar Ls.jar [-l] [-h] [-r] [-o output.file] directory_or_file")
            parser.printUsage(System.err)
            return
        }
        try {
            val ls = Ls(lFlag, hFlag, rFlag, outputFile, fileOrDir)
            val outputStream = outputFile?.let { File(it) }?.outputStream() ?: System.out
            ls.output(outputStream)
        } catch (e: Exception) {
            System.err.println(e.message)
        }
    }
}

fun main(args: Array<String>) {
    LsLauncher().launch(args)
}