import org.junit.jupiter.api.Test
import kotlin.test.assertEquals


class FindTest {
    @Test
    fun test1() {
        val find1 = Find()
        val list = setOf("1.txt", "2.txt", "3.txt", "4.txt", "5.txt", "6.txt", "10.txt", "7.txt")
        assertEquals(find1.find("./input", true, listOf("1.txt", "2.txt", "3.txt", "4.txt", "5.txt", "6.txt", "7.txt", "10.txt")), list)
    }

    @Test
    fun test2() {
        val find1 = Find()
        val list = setOf("3.txt", "10.txt", "7.txt")
        assertEquals(find1.find("./input/folder", true, listOf("1.txt", "7.txt", "2.txt", "3.txt", "10.txt")), list)
    }

    @Test
    fun test3() {
        val find1 = Find()
        val list = setOf("1.txt", "2.txt", "10.txt")
        assertEquals(find1.find("./input", true, listOf("1.txt", "15.txt", "2.txt", "10.txt")), list)
    }

    @Test
    fun test4() {
        val find1 = Find()
        val list = setOf("10.txt")
        assertEquals(find1.find("./input/folder", true, listOf("1.txt", "15.txt", "2.txt", "10.txt")), list)
    }
}
