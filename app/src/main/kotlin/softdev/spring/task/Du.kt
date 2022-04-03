package softdev.spring.task

import org.apache.commons.io.FileUtils
import java.io.File
import java.io.IOException
import kotlin.math.pow
import kotlin.jvm.Throws

class Du {
    lateinit var sumSizeString: String //для тестов
    var sumSize: Long = 0

    @Throws(IOException::class)
    fun du(h: Boolean, c: Boolean, si: Boolean, namesSplit: List<String>): Int {
        val files = mutableMapOf<String, Long>()
        for (name in namesSplit) {
            val file = File(name)
            val fileSize: Long = if (file.isFile) {
                file.length()
            } else if (file.isDirectory) {
                FileUtils.sizeOfDirectory(file)
            } else return 1
            files[file.name] = fileSize
            sumSize += fileSize
        }

        val base = if (si) 1000.0 else 1024.0
        val baseInt = base.toInt()

        val namesOutputList = mutableListOf<String>()
        if (h) {
            files["Total"] = sumSize
            for (file in files) {
                val size = file.value
                val name = file.key
                when {
                    size < base -> namesOutputList += "$name size is $size B"
                    base <= size && size < base * base -> namesOutputList += "$name size is ${size / baseInt} KB"
                    base * base <= size && size < base.pow(3) -> namesOutputList += "$name size is ${size / (baseInt * baseInt)} MB"
                    base.pow(3) <= size -> namesOutputList += "$name size is ${size.toDouble() / base.pow(3).toInt()} GB"
                }
            }
            sumSizeString = namesOutputList.last()
            namesOutputList.removeLast()
        } else {
            for (file in files) {
                namesOutputList += "${file.key} size is ${file.value / baseInt}"
            }
        }
        namesOutputList.forEach { println(it) }

        if (c) {
            if (!h) sumSizeString = "Total size is ${sumSize / baseInt}"
            println(sumSizeString)
        }
        return 0
    }
}