package softdev.spring.task

import org.apache.commons.io.FileUtils
import java.io.File
import java.io.IOException
import kotlin.math.pow
import kotlin.jvm.Throws

class Du {
    lateinit var sumSizeString: String //для тестов
    var sumSize: Long = 0 //для тестов
    @Throws(IOException::class)
    fun du(h: Boolean, c: Boolean, si: Boolean, namesSplit: List<String>): Int {
        val files = mutableMapOf<String, Long>()
        for (name in namesSplit) {
            val fileSize: Long = if (File(name).isFile) {
                File(name).length()
            } else if (File(name).isDirectory) {
                FileUtils.sizeOfDirectory(File(name))
            } else return 1
            files[File(name).name] = fileSize
        }

        val base = if (si) 1000.0 else 1024.0

        val namesOutputList = mutableListOf<String>()
        //
        if (h) {
            for (file in files) {
                val size = file.value
                val name = file.key
                sumSize += size
                when {
                    size < base -> namesOutputList += "$name size is $size B"
                    base <= size && size < base * base -> namesOutputList += "$name size is ${size / base.toInt()} KB"
                    base * base <= size && size < base.pow(3) -> namesOutputList += "$name size is ${size / (base * base).toInt()} MB"
                    base.pow(3) <= size -> namesOutputList += "$name size is ${size.toDouble() / base.pow(3)} GB"
                }
            }
        } else {
            for (file in files) {
                sumSize += file.value
                namesOutputList += "${file.key} size is ${file.value / base.toInt()}"
            }
        }
        namesOutputList.forEach { println(it) }

        //
        if (c) {
            if (h) {
                when {
                    sumSize < base -> sumSizeString = "Total is $sumSize B"
                    base <= sumSize && sumSize < base * base -> sumSizeString = "Total is ${sumSize / base.toInt()} KB"
                    base * base <= sumSize && sumSize < base.pow(3) -> sumSizeString =
                        "Total is ${sumSize / (base * base).toInt()} MB"
                    base.pow(3) <= sumSize -> sumSizeString = "Total is ${sumSize.toDouble() / base.pow(3).toInt()} GB"
                }
            } else {
                sumSizeString = "Total is ${sumSize / base.toInt()}"
            }
            println(sumSizeString)
        }
        //
        return 0
    }
}