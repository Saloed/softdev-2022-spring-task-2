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
                if (!isToLowercase(i, it).contains(Regex(isToLowercase(i, word))) && v) list.add(it)
                if (isToLowercase(i, it).contains(Regex(isToLowercase(i, word))) && !v) list.add(it)
            } else {
                if (!it.contains(word, ignoreCase = i) && v) list.add(it)
                if (it.contains(word, ignoreCase = i) && !v) list.add(it)
            }
        }
        list.forEach { println(it) }
        return list
    }
    private fun isToLowercase(i: Boolean, s: String): String {
        return if (i) s.lowercase() else s
    }
}
