import org.kohsuke.args4j.Argument
import org.kohsuke.args4j.CmdLineException
import org.kohsuke.args4j.CmdLineParser
import org.kohsuke.args4j.Option
import java.io.IOException

class FindLauncher {
    @Option(name = "-d", metaVar = "directory", required = true, usage = "use directory")
    private val directory: String? = null

    @Option(name = "-r", metaVar = "subdirectory", usage = "use subdirectory")
    private val subdirectory: Boolean = false

    @Argument(required = true, metaVar = "FileName", usage = "Input file name")
    private val fileName: List<String>? = null

    fun launch(args: Array<String>) {
        val parser = CmdLineParser(this)
        try {
            parser.parseArgument(*args)
        } catch (e: CmdLineException) {
            System.err.println(e.message)
            System.err.println("java -jar find.jar -d directory -r subdirectory FileName Input file name")
            parser.printUsage(System.err)
            return
        }
        val find1 = Find()
        try {
            find1.find(directory!!, subdirectory, fileName!!)
        } catch (e: IOException) {
            System.err.println(e.message)
        }
    }
}

fun main(args: Array<String>) {
    FindLauncher().launch(args)
}