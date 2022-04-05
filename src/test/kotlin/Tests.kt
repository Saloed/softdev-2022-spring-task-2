import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class Tests {
    @Test
    fun firstTestWithFile() {
        val file: MutableList<String> = Ls(true,
            true, true, null, "src/test/resources/someText").finalList
        assertEquals("[rwx 111 613KB 30.03.2022 20:17:26 someText]", file.toString())
    }

    @Test
    fun secondTestWithFile() {
        val file: MutableList<String> = Ls(true,
            true, false, null, "src/test/resources/someText").finalList
        assertEquals("[rwx 111 613KB 30.03.2022 20:17:26 someText]", file.toString())
    }

    @Test
    fun thirdTestWithFile() {
        val file: MutableList<String> = Ls(false,
            true, false, null, "src/test/resources/someText").finalList
        assertEquals("[rwx 613KB someText]", file.toString())
    }

    @Test
    fun firstTestwithDir() {
        val dir: MutableList<String> = Ls(false,
            false, false, null, "src/test/resources/someDir").finalList
        assertEquals("[anotherText, anotherText2]", dir.toString())
    }

    @Test
    fun secondTestwithDir() {
        val dir: MutableList<String> = Ls(false,
            false, true, null, "src/test/resources/someDir").finalList
        assertEquals("[anotherText2, anotherText]", dir.toString())
    }

    @Test
    fun thirdTestwithDir() {
        val dir: MutableList<String> = Ls(true,
            true, false, null, "src/test/resources/someDir").finalList
        assertEquals("[rwx 111 138KB 30.03.2022 21:06:10 anotherText, rwx 111 184KB 30.03.2022 21:13:50 anotherText2]", dir.toString())
    }

    @Test
    fun forthTestwithDir() {
        val dir: MutableList<String> = Ls(true,
            true, true, null, "src/test/resources/someDir").finalList
        assertEquals("[rwx 111 184KB 30.03.2022 21:13:50 anotherText2, rwx 111 138KB 30.03.2022 21:06:10 anotherText]", dir.toString())
    }

    @Test
    fun fifthTestwithDir() {
        val dir: MutableList<String> = Ls(true,
            true, false, null, "src/test/resources/someDir").finalList
        assertEquals("[rwx 111 138KB 30.03.2022 21:06:10 anotherText, rwx 111 184KB 30.03.2022 21:13:50 anotherText2]", dir.toString())
    }

    @Test
    fun sixthTestwithDir() {
        val dir: MutableList<String> = Ls(false,
            false, false, null, "src/test/resources/someDir2").finalList
        assertEquals("[a, ab.txt, b]", dir.toString())
    }

    @Test
    fun seventhTestwithDir() {
        val dir: MutableList<String> = Ls(false,
            false, true, null, "src/test/resources/someDir2").finalList
        assertEquals("[b, ab.txt, a]", dir.toString())
    }

}