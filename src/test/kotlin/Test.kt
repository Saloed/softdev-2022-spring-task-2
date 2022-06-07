import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class GrepTest {

    @Test
    fun test1() {
        val gr = Grep()
        val list = mutableListOf("dust 2", "gfg gfkp dust")
        assertEquals(gr.grep(r = false, i = false, v = false, word = "dust", inputName = "input/file1.txt"), list)
    }

    @Test
    fun test2() {
        val gr = Grep()
        val list = mutableListOf("ds fdg")
        assertEquals(gr.grep(r = false, i = true, v = true, word = "dust", inputName = "input/file1.txt"), list)
    }

    @Test
    fun test3() {
        val gr = Grep()
        val list = mutableListOf("4314513ogfhhghg", "fdgfg343434o")
        assertEquals(gr.grep(r = true, i = false, v = false, word = Regex("""\d+o""").toString(), inputName = "input/file2.txt"), list)
    }

    @Test
    fun test4() {
        val gr = Grep()
        val list = mutableListOf("1WORD")
        assertEquals(gr.grep(r = false, i = true, v = false, word = "1word", inputName = "input/file3.txt"), list)
    }
}