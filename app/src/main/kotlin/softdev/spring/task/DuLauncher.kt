package softdev.spring.task

import org.kohsuke.args4j.Argument
import org.kohsuke.args4j.CmdLineException
import org.kohsuke.args4j.CmdLineParser
import org.kohsuke.args4j.Option
import org.kohsuke.args4j.spi.StringArrayOptionHandler
import java.io.IOException

class DuLauncher {
    @Option(name = "-h", usage = "Make it readable", required = false, metaVar = "Readable")
    private var readable = false

    @Option(name = "-c", usage = "Prints sum size", required = false, metaVar = "Size form")
    private var sum = false

    @Option(name = "--si", usage = "Take base = 1000 instead of 1024", required = false, metaVar = "Base")
    private var base = false

    @Argument(required = true, metaVar = "fileNames", usage = "file1 file2 file3...", multiValued = true)
    private lateinit var someFile: List<String>

    fun launch(args: Array<String>) {
        val parser = CmdLineParser(this)

        try {
            parser.parseArgument(*args)
        } catch (e: CmdLineException) {
            System.err.println(e.message)
            System.err.println("du [-h] Readable [-c] Size form [--si] Base file1 file2 file3...")
            parser.printUsage(System.err)
            return
        }

        val duu = Du()
        try {
            println(duu.du(readable, sum, base, someFile))
        } catch (e: IOException) {
            System.err.println(e.message)
        }
    }
}

fun main(args: Array<String>) {
    DuLauncher().launch(args)
}
