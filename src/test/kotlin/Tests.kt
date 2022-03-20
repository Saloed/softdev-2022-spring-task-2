import org.junit.After
import org.junit.Before
import org.junit.Test
import java.io.File
import java.nio.file.Files
import java.nio.file.Path
import java.nio.file.Paths
import kotlin.io.path.absolutePathString
import kotlin.test.assertEquals

class Tests {
    companion object {
        private const val TEST_DIR = "./build/testDir"
        private const val TEST_SUB_DIR = "./build/testDir/testSubDir"
        private const val TEST_FILE_NAME = "file.txt"
    }

    @Before
    fun before() {
        Files.createDirectory(Paths.get(TEST_DIR))
        Files.createDirectory(Paths.get(TEST_SUB_DIR))
        Files.createFile(Paths.get("$TEST_DIR/$TEST_FILE_NAME"))
        Files.createFile(Paths.get("$TEST_SUB_DIR/$TEST_FILE_NAME"))
    }

    @Test
    fun withoutSubDirTest() {
        assertEquals(
            listOf(Paths.get("$TEST_DIR/$TEST_FILE_NAME").absolutePathString()),
            readDirectory(TEST_DIR, false, TEST_FILE_NAME)
        )
    }

    @Test
    fun withSubDirTest() {
        assertEquals(
            listOf(
                Paths.get("$TEST_DIR/$TEST_FILE_NAME").absolutePathString(),
                Paths.get("$TEST_SUB_DIR/$TEST_FILE_NAME").absolutePathString()
            ), readDirectory(
                TEST_DIR, true, TEST_FILE_NAME
            )
        )
    }

    @Test
    fun wrongDirectoryNamePathTest() {
        assertEquals(listOf(), readDirectory("rffkfkkf", false, TEST_FILE_NAME))
    }

    @Test
    fun wrongFileNamePathTest() {
        assertEquals(listOf(), readDirectory(TEST_DIR, false, "fgkgfjdk"))
    }

    @After
    fun after() {
        Files.walk(Paths.get(TEST_DIR))
            .sorted(Comparator.reverseOrder())
            .map(Path::toFile)
            .forEach(File::delete)
    }
}