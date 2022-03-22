import java.io.File
import java.io.IOException
import kotlin.jvm.Throws

class Grep {
    @Throws(IOException::class)
    fun grep(r: Boolean, i: Boolean, v: Boolean, word: String, inputName: String): List<String> {
        val list = mutableListOf<String>()
        val inputStrings = File(inputName).readLines()
        inputStrings.forEach {
            if (r) {
                if (i) {
                    if (!it.lowercase().contains(Regex(word.lowercase())) && v) {
                        println(it)
                        list.add(it)
                    }
                    if (it.lowercase().contains(Regex(word.lowercase())) && !v) {
                        println(it)
                        list.add(it)
                    }
                } else {
                    if (!it.contains(Regex(word)) && v) {
                        println(it)
                        list.add(it)
                    }
                    if (it.contains(Regex(word)) && !v) {
                        println(it)
                        list.add(it)
                    }
                }
            } else {
                if (!it.contains(word, ignoreCase = i) && v) {
                    println(it)
                    list.add(it)
                }
                if (it.contains(word, ignoreCase = i) && !v) {
                    println(it)
                    list.add(it)
                }
            }
        }
        return list
    }
}