import java.io.*
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList
import kotlin.jvm.Throws
import kotlin.math.pow

/**
 * Вывод содержимого указанной в качестве аргумента директории в виде отсортированного списка имен файлов.
 *
 *
 *
Флаг -l (long) переключает вывод в длинный формат, в котором, кроме имени файла, указываются права на выполнение/чтение/запись в виде битовой маски XXX, время последней модификации и размер в байтах.

Флаг -h (human-readable) переключает вывод в человеко-читаемый формат (размер в кило-, мега- или гигабайтах, права на выполнение в виде rwx).

Флаг -r (reverse) меняет порядок вывода на противоположный.

Флаг -o (output) указывает имя файла, в который следует вывести результат; если этот флаг отсутствует, результат выводится в консоль.



В случае, если в качестве аргумента указан файл, а не директория, следует вывести информацию об этом файле.
Command Line: ls [-l] [-h] [-r] [-o output.file] directory_or_file
Actually will be: java -jar ls.jar [-l] [-h] [-r] [-o output.file] directory_or_file
 */

class Ls(private val lFlag: Boolean, private val hFlag: Boolean, private val rFlag: Boolean, private val outputFile: String?, private val fileOrDir: String?) {

    private fun xxx(file: File?): String {
        val xxx = mutableListOf("0", "0", "0")
        if (file!!.canRead()) xxx[0] = "1"
        if (file.canWrite()) xxx[1] = "1"
        if (file.canExecute()) xxx[2] = "1"
        return (xxx.joinToString())
    }

    private fun rwx(file: File?): String {
        val rwx = mutableListOf<String>()
        if (file!!.canRead()) rwx.add("r")
        if (file.canWrite()) rwx.add("w")
        if (file.canExecute()) rwx.add("x")
        return rwx.joinToString()
    }

    private fun toHumanReadableSize(file: File?): String {
        return when (file!!.length()) {
            in (0..1024) -> listOf(file.length().toString(), "B").joinToString(" ")
            in (1024..1024.0.pow(2).toInt()) -> listOf((file.length() / 1024).toString(), "KB").joinToString(" ")
            in (1024..1024.0.pow(3).toInt()) -> listOf((file.length() / 1024.0.pow(2).toInt()).toString(), "MB").joinToString(" ")
            in (1024..1024.0.pow(4).toInt()) -> listOf((file.length() / 1024.0.pow(3).toInt()).toString(), "GB").joinToString(" ")
            in (1024..1024.0.pow(5).toInt()) -> listOf((file.length() / 1024.0.pow(4).toInt()).toString(), "TB").joinToString(" ")
            in (1024..1024.0.pow(6).toInt()) -> listOf((file.length() / 1024.0.pow(6).toInt()).toString(), "PB").joinToString(" ")
            else -> "The size of file is bigger then 1024 PB"
        }
    }

    private fun toFile(file: File?): String {
        val nameOfFile = file!!.name
        var (rwxOrXxx, lastModify, size) = listOf("", "", "")
        if (lFlag) {
            lastModify = SimpleDateFormat("dd.MM.yyyy HH:mm:ss").format(Date(file.lastModified()))
            size = file.length().toString()
            rwxOrXxx = xxx(file)
        }
        if (hFlag) {
            size = toHumanReadableSize(file)
            rwxOrXxx = rwx(file)
        }
        return if (rFlag) listOf(rwxOrXxx, size, lastModify, nameOfFile).joinToString(" ")
        else listOf(nameOfFile, lastModify, size, rwxOrXxx).joinToString(" ")
    }

    private fun toFileList(file: File): ArrayList<File?> {
        val fileList = ArrayList<File?>()
        if (file.isDirectory) {
            if (file.listFiles() != null)
                file.listFiles()!!.forEach { if (it != null) fileList.add(it) }
        } else fileList.add(file)
        return fileList
    }

    private fun toDir(fileList: ArrayList<File?>): ArrayList<String> {
        val sortedList = ArrayList<String>()
        val actualList = if (rFlag) {
            fileList.asReversed()
        } else fileList
        actualList.forEach { if (it != null) sortedList.add(toFile(it)) }
        return sortedList
    }

    val finalList: ArrayList<String> = toDir(toFileList(File(fileOrDir!!)))

    @Throws(IOException::class)
    fun writeToFile(fileList: ArrayList<String>) {
        if (outputFile != null) {
            File(outputFile).bufferedWriter().use {
                for (file in fileList) it.write(file)
            }
            println("Information is recorded in $outputFile")
        }
    }

    @Override
    override fun equals(other: Any?): Boolean = super.equals(other)

    @Override
    override fun hashCode(): Int {
        var result = lFlag.hashCode()
        result = 31 * result + hFlag.hashCode()
        result = 31 * result + rFlag.hashCode()
        result = 31 * result + (outputFile?.hashCode() ?: 0)
        result = 31 * result + (fileOrDir?.hashCode() ?: 0)
        result = 31 * result + finalList.hashCode()
        return result
    }
}

