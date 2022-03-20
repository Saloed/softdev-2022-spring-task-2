import kotlinx.cli.ArgParser
import kotlinx.cli.ArgType
import kotlinx.cli.default
import java.io.IOException
import java.nio.file.Files
import java.nio.file.Paths
import java.util.stream.Collectors
import kotlin.io.path.exists

fun readDirectory(directory: String, subdirectories: Boolean, fileName: String): List<String> {
    try {
        if (subdirectories) {
            return Files.walk(Paths.get(directory))
                .filter { Files.isRegularFile(it) && it.toFile().name == fileName }
                .map { it.toFile().absolutePath }
                .collect(Collectors.toList())
        } else {
            val pathToFile = if (directory.isEmpty())
                Paths.get(fileName)
            else
                Paths.get("$directory\\$fileName")

            val pathToDirectory = Paths.get(directory)

            if (!pathToDirectory.exists()) {
                println(Constants.WRONG_DIRECTORY_PATH)
            } else {
                if (pathToFile.exists())
                    return listOf(pathToFile.toFile().absolutePath)
            }
        }
    } catch (e: IOException) {
        println(Constants.WRONG_DIRECTORY_PATH)
    }
    return listOf()
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
    val fileName by parser.argument(ArgType.String, description = Constants.FILENAME_DESCRIPTION)

    parser.parse(args)
    val resultData = readDirectory(directory, subdirectories, fileName)
    printData(resultData)
}




