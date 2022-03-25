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
        private val TEST_DIR = Paths.get(".").resolve("build").resolve("testDir")
        private val TEST_SUB_DIR = TEST_DIR.resolve("testSubDir")
        private const val TEST_FILE_NAME = "file.txt"
    }

    @Before
    fun before() {
        Files.createDirectory(TEST_DIR)
        Files.createDirectory(TEST_SUB_DIR)
        Files.createFile(TEST_DIR.resolve(TEST_FILE_NAME))
        Files.createFile(TEST_SUB_DIR.resolve(TEST_FILE_NAME))
    }

    @Test
    fun withoutSubDirTest() {
        assertEquals(
            UtilResult.Success(setOf(TEST_DIR.resolve(TEST_FILE_NAME).absolutePathString())),
            readDirectory(TEST_DIR.absolutePathString(), false, TEST_FILE_NAME)
        )
    }

    @Test
    fun withSubDirTest() {
        assertEquals(
            UtilResult.Success(
                setOf(
                    TEST_DIR.resolve(TEST_FILE_NAME).absolutePathString(),
                    TEST_SUB_DIR.resolve(TEST_FILE_NAME).absolutePathString()
                )
            ), readDirectory(
                TEST_DIR.absolutePathString(), true, TEST_FILE_NAME
            )
        )
    }

    @Test
    fun wrongDirectoryNamePathTest() {
        assertEquals(UtilResult.Error(Constants.WRONG_DIRECTORY_PATH), readDirectory("rffkfkkf", false, TEST_FILE_NAME))
    }

    @Test
    fun wrongFileNamePathTest() {
        assertEquals(UtilResult.Success(emptySet()), readDirectory(TEST_DIR.absolutePathString(), false, "fgkgfjdk"))
    }

    @After
    fun after() {
        Files.walk(TEST_DIR)
            .sorted(Comparator.reverseOrder())
            .map(Path::toFile)
            .forEach(File::delete)
    }
}