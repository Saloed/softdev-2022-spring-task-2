import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class Tests {
    @Test
    fun firstTestWithFile() {
        val file: ArrayList<String> = Ls(true,
            true, true, null, "src/test/resources/someText").finalList
        assertEquals("[someText 613KB 30.03.2022 20:17:26 111 rwx]", file.toString())
    }

    @Test
    fun secondTestWithFile() {
        val file: ArrayList<String> = Ls(true,
            true, false, null, "src/test/resources/someText").finalList
        assertEquals("[rwx 111 613KB 30.03.2022 20:17:26 someText]", file.toString())
    }

    @Test
    fun thirdTestWithFile() {
        val file: ArrayList<String> = Ls(false,
            true, false, null, "src/test/resources/someText").finalList
        assertEquals("[rwx 613KB someText]", file.toString())
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
        assertEquals("[rwx 111 138KB 30.03.2022 21:06:10 anotherText, rwx 111 184KB 30.03.2022 21:13:50 anotherText2]", dir.toString())
    }

    @Test
    fun forthTestwithDir() {
        val dir: ArrayList<String> = Ls(true,
            true, true, null, "src/test/resources/someDir").finalList
        assertEquals("[anotherText2 184KB 30.03.2022 21:13:50 111 rwx, anotherText 138KB 30.03.2022 21:06:10 111 rwx]", dir.toString())
    }
}