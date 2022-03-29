package softdev.spring.task

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class DuTest {
    @Test
    fun firstTest() {
        val duu = Du()
        val c = true
        val h = false
        val si = true
        duu.du(h, c, si, "testFiles/a--testFiles/AOT.jpg")
        assertEquals(duu.du(h, c, si, "testFiles/a--testFiles/AOT.jpg"), 0)
        assertEquals("Total is 391", duu.sumSizeString)
        assertEquals(duu.size, 391214)
    }

    @Test
    fun secondTest() {
        val duu = Du()
        val c = true
        val h = true
        val si = false
        assertEquals(1, duu.du(h, c, si, "testFiles/a--testsdfsdfsFiles/AOT.jpg"))
        duu.du(h, c, si, "testFiles/a--testFiles/AOT.jpg")
        assertEquals("Total is 382 KB", duu.sumSizeString)
    }

    @Test
    fun thirdTest() {
        val duu = Du()
        val c = false
        val h = false
        val si = false
        duu.du(h, c, si, "testFiles")
        assertEquals(duu.size, 11739802)
    }
}