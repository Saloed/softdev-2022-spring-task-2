import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class GrepTest {

    @Test
    fun test1() {
        val gr = Grep()
        val list = mutableListOf("flower 1", "re df flower")
        assertEquals(gr.grep(r = false, i = false, v = false, word = "flower", inputName = "input/test1.txt"), list)
    }

    @Test
    fun test2() {
        val gr = Grep()
        val list = mutableListOf("fl hts")
        assertEquals(gr.grep(r = false, i = true, v = true, word = "flower", inputName = "input/test1.txt"), list)
    }

    @Test
    fun test3() {
        val gr = Grep()
        val list = mutableListOf("234234sjkfh", "jvod345s")
        assertEquals(gr.grep(r = true, i = false, v = false, word = Regex("""\d+s""").toString(), inputName = "input/regex.txt"), list)
    }
}