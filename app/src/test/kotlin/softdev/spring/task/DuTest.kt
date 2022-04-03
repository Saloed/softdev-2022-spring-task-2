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
        duu.du(h, c, si, listOf("testFiles/a", "testFiles/AOT.jpg"))
        assertEquals("Total size is 391", duu.sumSizeString)
        assertEquals(duu.du(h, c, si, listOf("testFiles/a", "testFiles/AOT.jpg")), 0)
    }

    @Test
    fun secondTest() {
        val duu = Du()
        val c = true
        val h = true
        val si = false
        val a = listOf("testFiles/a", "testFiles/AOT.jpg")
        duu.du(h, c, si, a)
        assertEquals("Total size is 382 KB", duu.sumSizeString)
    }

    @Test
    fun thirdTest() {
        val duu = Du()
        val c = false
        val h = false
        val si = false
        duu.du(h, c, si, listOf("testFiles"))
        assertEquals(duu.sumSize, 11739802)
    }

    @Test
    fun fourthTest() {
        val duu = Du()
        val c = true
        val h = true
        val si = false
        val a = listOf("testFiles/a", "testFiles/AOT.jpg")
        val b = listOf("testFiles/a", "testsdfsdfsFiles/AOT.jpg")
        assertEquals(1, duu.du(h, c, si, b))
    }
}