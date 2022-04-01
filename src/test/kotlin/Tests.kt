import org.junit.jupiter.api.Test
import java.io.File
import java.io.IOException
import java.nio.file.Files
import java.nio.file.Paths
import kotlin.test.assertEquals
import kotlin.test.assertTrue


class Tests {
    @Test
    fun firstTestWithFile() {
        val file: ArrayList<String> = Ls(true,
            true, true, null, "src/test/resources/someText").finalList
        assertEquals("[111 rwx 613KB 30.03.2022 20:17:26 someText]", file.toString())
    }

    @Test
    fun secondTestWithFile() {
        val file: ArrayList<String> = Ls(true,
            true, false, null, "src/test/resources/someText").finalList
        assertEquals("[someText 613KB 30.03.2022 20:17:26 rwx 111]", file.toString())
    }

    @Test
    fun thirdTestWithFile() {
        val file: ArrayList<String> = Ls(false,
            true, false, null, "src/test/resources/someText").finalList
        assertEquals("[someText 613KB rwx]", file.toString())
    }

    @Test
    @Throws(IOException::class)
    fun forthTestWithFile() {
        val ls = Ls(false,
            false, false, "src/test/resources/emptyFile", "src/test/resources/someText")
        val expected = File("src/test/resources/emptyFile")
        val file: ArrayList<String> = ls.finalList
        ls.writeToFile(file)
        assertTrue(expected.exists())
        assertEquals("someText", String(Files.readAllBytes(Paths.get("src/test/resources/emptyFile"))))
        expected.delete()
    }

    @Test
    fun firstTestwithDir() {
        val dir: ArrayList<String> = Ls(false,
            false, false, null, "src/test/resources/someDir").finalList
        assertEquals("[anotherText, anotherText2]", dir.toString())
    }

    @Test
    fun secondTestwithDir() {
        val dir: ArrayList<String> = Ls(false,
            false, true, null, "src/test/resources/someDir").finalList
        assertEquals("[anotherText2, anotherText]", dir.toString())
    }

    @Test
    fun thirdTestwithDir() {
        val dir: ArrayList<String> = Ls(true,
            true, false, null, "src/test/resources/someDir").finalList
        assertEquals("[anotherText 138KB 30.03.2022 21:06:10 rwx 111, anotherText2 184KB 30.03.2022 21:13:50 rwx 111]", dir.toString())
    }

    @Test
    fun forthTestwithDir() {
        val dir: ArrayList<String> = Ls(true,
            true, true, null, "src/test/resources/someDir").finalList
        assertEquals("[111 rwx 184KB 30.03.2022 21:13:50 anotherText2, 111 rwx 138KB 30.03.2022 21:06:10 anotherText]", dir.toString())
    }

    @Test
    @Throws(IOException::class)
    fun fifthTestWithDir() {
        val ls = Ls(false,
            false, false, "src/test/resources/emptyFile", "src/test/resources/someDir")
        val expected = File("src/test/resources/emptyFile")
        val file: ArrayList<String> = ls.finalList
        ls.writeToFile(file)
        assertTrue(expected.exists())
        assertEquals("anotherTextanotherText2", String(Files.readAllBytes(Paths.get("src/test/resources/emptyFile"))))
        expected.delete()
    }
}