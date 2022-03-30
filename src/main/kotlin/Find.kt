import java.io.File
import java.io.IOException
import java.nio.file.Files
import java.nio.file.Path
import java.util.*


class Find {
    fun find(directory: String, subdirectory: Boolean, fileNames: Set<String>): Set<String> {

        val result = mutableSetOf<String>()
        val basedDirectory = File(directory)

        if (!subdirectory) {
            for (file in fileNames) {
                val d1 = "$directory\\$file"
                val path = Path.of(d1)
                if (Files.exists(path)) {
                    result.add(file)
                }
            }
        } else {

            val fileTree = PriorityQueue<File>()
            fileTree.addAll(basedDirectory.listFiles()!!)

            while (!fileTree.isEmpty()) {
                val currentFile = fileTree.remove()
                if (currentFile.isDirectory) {
                    fileTree.addAll(currentFile.listFiles()!!)
                } else {
                    if (currentFile.name in fileNames) {
                        result.add(currentFile.name)
                    }
                }

            }
        }
//        if (result.isEmpty()) throw IOException("В текущей директории файл(-ы) не найдены")
        println(result)
        return result
    }
}
