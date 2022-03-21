import kotlinx.cli.ArgParser
import kotlinx.cli.ArgType
import kotlinx.cli.default
import kotlinx.cli.vararg
import java.nio.file.Files
import java.nio.file.Paths
import java.util.stream.Collectors
import kotlin.io.path.exists

fun readDirectory(directory: String, subdirectories: Boolean, vararg fileNames: String): UtilResult {
    val pathToDirectory = Paths.get(directory)
    if (!pathToDirectory.exists())
        return UtilResult.Error(Constants.WRONG_DIRECTORY_PATH)

    val resultList = mutableListOf<String>()
    for (fileName in fileNames) {
        if (subdirectories) {
            resultList.addAll(Files.walk(Paths.get(directory))
                .filter { Files.isRegularFile(it) && it.toFile().name == fileName }
                .map { it.toFile().absolutePath }
                .collect(Collectors.toList()))
        } else {
            val pathToFile = if (directory.isEmpty())
                Paths.get(fileName)
            else
                Paths.get("$directory\\$fileName")

            if (pathToFile.exists())
                resultList.add(pathToFile.toFile().absolutePath)
        }
    }
    return UtilResult.Success(resultList)
}

fun printResult(result: UtilResult) {
    when (result) {
        is UtilResult.Success -> result.data.forEach { println(it) }
        is UtilResult.Error -> println(result)
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
    val result = readDirectory(directory, subdirectories, *fileName.toTypedArray())
    printResult(result)
}




