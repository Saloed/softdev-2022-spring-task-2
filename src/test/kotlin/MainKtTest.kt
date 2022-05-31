import java.io.File
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class Tests {
        fun assertFileContent(name: String, expectedContent: String) {
            val file = File(name)
            val content = file.readLines().joinToString("\n")
            assertEquals(expectedContent, content)
        }
    @Test
    fun flagOut(){
        flagOut(listOf("files/file1.txt", "files/file2.txt"),"files/output.txt" )
        assertFileContent("files/output.txt", "qwerqaadswerqwer\n" +
                "asdfasdfasdf\n" +
                "cxvxczvzv\n" +
                "ghkjghk\n" +
                "uyityu\n" +
                "ffgh\n" +
                "popopopdkd\n" +
                "1234567\n" +
                "10928910\n" +
                "65757484930\n" +
                "110239482571245812304912347120374\n" +
                "12341234123412345\n" +
                "55555555555\n" +
                "111111111111\n" +
                "22222222222\n" +
                "1010109000000000001019192\n" +
                "12342143\n" +
                "123412341234\n" +
                "combined files with the tar flag 7-11")

        flagOut(listOf("files/file1.txt", "files/file2.txt", "files/file3.txt"),"files/output.txt" )
        assertFileContent("files/output.txt", "qwerqaadswerqwer\n" +
                "asdfasdfasdf\n" +
                "cxvxczvzv\n" +
                "ghkjghk\n" +
                "uyityu\n" +
                "ffgh\n" +
                "popopopdkd\n" +
                "1234567\n" +
                "10928910\n" +
                "65757484930\n" +
                "110239482571245812304912347120374\n" +
                "12341234123412345\n" +
                "55555555555\n" +
                "111111111111\n" +
                "22222222222\n" +
                "1010109000000000001019192\n" +
                "12342143\n" +
                "123412341234\n" +
                "ййййййй\n" +
                "ффф\n" +
                "\n" +
                "\n" +
                "----\n" +
                "фыва\n" +
                "--\n" +
                "???\n" +
                "**)_(_(*\n" +
                "\n" +
                "000\n" +
                "combined files with the tar flag 7-11-11")
    }
    @Test
    fun flagU(){
        flagU("files/output.txt")
        assertFileContent("files/NewFile1.txt", "qwerqaadswerqwer\n" +
                "asdfasdfasdf\n" +
                "cxvxczvzv\n" +
                "ghkjghk\n" +
                "uyityu\n" +
                "ffgh\n" +
                "popopopdkd")
        assertFileContent("files/NewFile2.txt", "1234567\n" +
                "10928910\n" +
                "65757484930\n" +
                "110239482571245812304912347120374\n" +
                "12341234123412345\n" +
                "55555555555\n" +
                "111111111111\n" +
                "22222222222\n" +
                "1010109000000000001019192\n" +
                "12342143\n" +
                "123412341234")
        assertFileContent("files/NewFile3.txt", "ййййййй\n" +
                "ффф\n" +
                "\n" +
                "\n" +
                "----\n" +
                "фыва\n" +
                "--\n" +
                "???\n" +
                "**)_(_(*\n" +
                "\n" +
                "000")
    }

}