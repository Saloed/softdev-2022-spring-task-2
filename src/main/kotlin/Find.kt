import kotlinx.cli.ArgParser
import kotlinx.cli.ArgType
import kotlinx.cli.default
import kotlinx.cli.vararg
import java.nio.file.Files
import java.nio.file.Paths
import java.util.stream.Collectors
import kotlin.io.path.exists

fun readDirectory(directory: String, subdirectories: Boolean, vararg fileNames: String): List<String> {
    val result = mutableListOf<String>()
    val pathToDirectory = Paths.get(directory)
    if (!pathToDirectory.exists()) {
        println(Constants.WRONG_DIRECTORY_PATH)
    } else {
        for (fileName in fileNames) {
            if (subdirectories) {
                result.addAll(Files.walk(Paths.get(directory))
                    .filter { Files.isRegularFile(it) && it.toFile().name == fileName }
                    .map { it.toFile().absolutePath }
                    .collect(Collectors.toList()))
            } else {
                val pathToFile = if (directory.isEmpty())
                    Paths.get(fileName)
                else
                    Paths.get("$directory\\$fileName")

                if (pathToFile.exists())
                    result.add(pathToFile.toFile().absolutePath)
            }
        }
    }
    return result
}

fun printData(data: List<String>) {
    data.forEach {
        println(it)
    }
}

fun main(args: Array<String>) {
    val parser = ArgParser(Constants.PROGRAM_NAME, strictSubcommandOptionsOrder = true)
    val subdirectories by parser.option(
        ArgType.Boolean,
        shortName = Constants.SUBDIRECTORIES_SHORT_NAME,
        description = Constants.SUBDIRECTORIES_DESCRIPTION
    ).default(false)
    val directory by parser.option(
        ArgType.String,
        shortName = Constants.DIRECTORY_SHORT_NAME,
        description = Constants.DIRECTORY_DESCRIPTION
    ).default("")
    val fileName by parser.argument(ArgType.String, description = Constants.FILENAME_DESCRIPTION).vararg()

    parser.parse(args)
    val resultData = readDirectory(directory, subdirectories, *fileName.toTypedArray())
    printData(resultData)
}




