package softdev.spring.task

import org.apache.commons.io.FileUtils
import java.io.File
import java.io.FileNotFoundException
import kotlin.math.pow

class Du {
    var sumSizeString: String? = null //для тестов
    var size: Long = 0 //для тестов
    fun du(h: Boolean, c: Boolean, si: Boolean, fileNames: String): Int {
        val namesSplit = fileNames.trim().split("--")
        val files = mutableMapOf<String, Long>()
        for (name in namesSplit) {
            var fileSize: Long;
            if (File(name).isFile) {
                fileSize = File(name).length()
            } else if (File(name).isDirectory) {
                fileSize = FileUtils.sizeOfDirectory(File(name))
            } else return 1
            files[File(name).name] = fileSize
        }

        val base = if (si) 1000.0 else 1024.0

        val namesOutputList = mutableListOf<String>()
        var sumSize: Long = 0
        if (h) {
            for (File in files) {
                val size = File.value
                val name = File.key
                sumSize += size
                when {
                    size < base -> namesOutputList += "$name size is $size B"
                    base <= size && size < base * base -> namesOutputList += "$name size is ${size / base.toInt()} KB"
                    base * base <= size && size < base.pow(3) -> namesOutputList += "$name size is ${size / (base * base).toInt()} MB"
                    base.pow(3) <= size -> namesOutputList += "$name size is ${size.toDouble() / base.pow(3)} GB"
                }
            }
        } else {
            for (File in files) {
                sumSize += File.value
                namesOutputList += "${File.key} size is ${File.value / base.toInt()}"
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
        size = sumSize
        return 0
    }
}