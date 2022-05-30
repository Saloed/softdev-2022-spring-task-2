package code;

import org.kohsuke.args4j.CmdLineException;

import java.io.*;
import java.nio.file.Files;
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

    public static void main(String[] args, int number) throws CmdLineException, IOException, NullPointerException {
        UniqParse arguments = new UniqParse(args);
        FileWriter fw = new FileWriter("C:\\Users\\danii\\IdeaProjects\\softdev-2022-spring-task-2\\.idea\\output.txt");
        BufferedWriter bw = new BufferedWriter(fw);
        try {
            for (String s : arguments.inputFileName()) {

                if (arguments.ignoreCase()) {
                    List<String> lines = Files.readAllLines(new File(s).toPath());
                    String lastLine = "";
                    for (String line : lines) {
                        if (line.equalsIgnoreCase(lastLine)) {
                            lastLine = line;
                            bw.write(line);
                            bw.newLine();
                        }
                    }
                }

                if (arguments.ignoreN()) {
                    List<String> lines = Files.readAllLines(new File(s).toPath());
                    String lastLine = "";
                    for (String line : lines) {
                        ignore(line, lastLine, number);
                        bw.write(line.substring(number));
                        bw.newLine();
                    }
                }

                if (arguments.uniqueStrings()) {
                    List<String> lines = Files.readAllLines(new File(s).toPath());
                    String lastLine = "";
                    for (String line : lines) {
                        if (!line.equals(lastLine)) {
                            bw.write(line);
                            bw.newLine();
                        }
                    }
                }

                if (arguments.numOfStrings()) {
                    List<String> lines = Files.readAllLines(new File(s).toPath());
                    String lastLine = "";
                    int sameStrings = 0;
                    for (String line : lines) {
                        if (line.equals(lastLine)) {
                            sameStrings += 1;
                        }
                        bw.write(sameStrings);
                        bw.write(line);
                        bw.newLine();
                    }
                }
            }
        } catch (NullPointerException e) {
            System.out.println("ERROR " + e.getMessage());
        }
    }

    private static void ignore(String lineOne, String lineTwo, int count) {
        lineOne.substring(count).equals(lineTwo.substring(count));
    }

    private static void ignoreN(String lineOne, String lineTwo, int number) {

    }
}
