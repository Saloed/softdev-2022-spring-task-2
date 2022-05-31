package code;

import org.kohsuke.args4j.CmdLineException;

import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;


/**
 * uniq
 * Объединение последовательностей одинаковых идущих подряд строк в файле в одну:
 * file задаёт имя входного файла. Если параметр отсутствует, следует считывать текст с консоли.
 * Флаг -o ofile  задаёт имя выходного файла. Если параметр отсутствует, следует выводить результаты на консоль.
 * Флаг -i означает, что при сравнении строк следует не учитывать регистр символов.
 * Флаг -s N означает, что при сравнении строк следует игнорировать первые N символов каждой строки.
 * Выводить нужно первую строку.
 * Флаг -u означает, что следует выводить в качестве результата только уникальные строки (т.е. те, которые не были
 * объединены с соседними).
 * Флаг -с означает, что перед каждой строкой вывода следует вывести количество строк, которые были заменены данной
 * (т.е. если во входных данных было 2 одинаковые строки, в выходных данных должна быть одна с префиксом “2”).
 * <p>
 * Command line: uniq [-i] [-u] [-c] [-s num] [-o ofile] [file]
 * <p>
 * В случае, когда какое-нибудь из имён файлов указано неверно, следует выдать ошибку.
 */

public class CombiningStrings {

    public static void main(String[] args) throws CmdLineException, IOException, NullPointerException {
        UniqParse arguments = new UniqParse(args);
        List<String> resultLines = new ArrayList<>();
        List<Integer> resultNumbers = new ArrayList<>();
        List<String> lines = Files.readAllLines(Path.of(arguments.inputFileName()));

        for (String line : lines) {
            if (resultLines.isEmpty()) {
                resultLines.add(line);
                resultNumbers.add(1);
                continue;
            }
            if (equalsIgnoreCaseOrNot(
                    line.substring(arguments.ignoreN()),
                    resultLines.get(resultLines.size() - 1).substring(arguments.ignoreN()),
                    arguments.ignoreCase()
            )) {
                int num = resultNumbers.remove(resultNumbers.size() - 1);
                resultNumbers.add(num + 1);
            } else {
                resultLines.add(line);
                resultNumbers.add(1);
            }
        }
        File outputFile = new File(arguments.outputFileName());
        //outputFile.createNewFile();
        BufferedWriter bw = Files.newBufferedWriter(outputFile.toPath());

        for (int i = 0; i < resultLines.size(); i++) {
            bw.write(reformatString(
                    resultLines.get(i),
                    resultNumbers.get(i),
                    arguments.uniqueStrings(),
                    arguments.numOfStrings())
            );
            if (i != resultLines.size() - 1) bw.newLine();
        }
        bw.close();
    }

    private static boolean equalsIgnoreCaseOrNot(String s1, String s2, boolean ignoreCase) {
        if (ignoreCase) return s1.equalsIgnoreCase(s2);
        else return s1.equals(s2);
    }

    private static String reformatString(String line, int lineNumber, boolean uniqueStrings, boolean numberPrefix) {
        String string = line;
        if (numberPrefix) string = lineNumber + string;
        if (uniqueStrings && lineNumber > 1) string = "";
        return string;
    }
}
