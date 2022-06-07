import java.io.File
import java.io.IOException
import kotlin.jvm.Throws

class Grep {
    @Throws(IOException::class)
    fun grep(r: Boolean, i: Boolean, v: Boolean, word: String, inputName: String): List<String> {
        val list = mutableListOf<String>()
        val givenString = File(inputName).readLines()
        for (elem in givenString) {
            if (r) {
                if (!isToLowercase(i, elem).contains(Regex(isToLowercase(i, word))) && v) list.add(elem)
                if (isToLowercase(i, elem).contains(Regex(isToLowercase(i, word))) && !v) list.add(elem)
            } else {
                if (!elem.contains(word, ignoreCase = i) && v) list.add(elem)
                if (elem.contains(word, ignoreCase = i) && !v) list.add(elem)
            }
        }
        for (i in list) {
            println(i)
        }
        return list
    }

    private fun isToLowercase(i: Boolean, s: String): String {
        return if (i) s.lowercase() else s
    }
}