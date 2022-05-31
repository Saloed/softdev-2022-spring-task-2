import java.io.File

fun main(args: Array<String>) {
    val input = Parser()
    input.Pars(args)
    val arguments = input.arguments.drop(1)
    if (input.out != null) flagOut(arguments, input.out)
    if (input.u != null)
        if (check(input.u))
            flagU(input.u)
        else
            throw IllegalArgumentException("this file wasn't merged")
}
fun flagOut (inputName: List<String>,outputName: String){
    val writer = File(outputName).bufferedWriter()
    var str = ""
    var input: List<String>
    for (i in inputName.indices){
        input = File(inputName[i]).readLines()
        val t = File(inputName[i]).readLines().size
        for (j in input.indices) {
            writer.write(input[j])
            writer.newLine()
        }
        str += "$t-"
    }
    str = str.dropLast(1)
    writer.write("combined files with the tar flag $str")
    writer.close()
}
fun check (file : String):Boolean {
    val inputName = File(file).readLines()
         return inputName.last().contains("combined files with the tar flag")
}
fun flagU(inputName: String){
    var t = 0
    val input = File(inputName).readLines().dropLast(1)
    val last = File(inputName).readLines().last().replace("combined files with the tar flag ", "")
    val counts = last.split("-")
    for (i in counts.indices) {
        val writer = File("files/NewFile${i+1}.txt").bufferedWriter()
        writer.write(input[t])
        t += 1
        for (j in 2..counts[i].toInt()){
            writer.newLine()
            writer.write(input[t])
            t += 1
        }
        writer.close()
    }
}