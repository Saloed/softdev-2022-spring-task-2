package ls;

import org.kohsuke.args4j.CmdLineException;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Objects;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        for (int i = 0; ; i++) {
            try {
                Args arguments;
                if (i == 0) arguments = new Args(args);
                else {
                    Scanner scanner = new Scanner(System.in);
                    String stringArgs = scanner.nextLine();
                    if (Objects.equals(stringArgs, "exit")) return;
                    arguments = new Args(stringArgs.split("\\s+"));
                }
                start(arguments);
                return;
            } catch (CmdLineException e) {
                System.out.println("Неправильно введены аргументы");
            } catch (IOException e) {
                System.out.println("Что-то пошло не так с директорией");
            } catch (IllegalArgumentException e) {
                System.out.println("Флаг -h не может быть без -l");
            }
        }
    }

    public static void start(Args args) throws IOException {
        CommandsList listOfFiles = new CommandsList(args.dir);
        if (!args.l && args.h) throw new IllegalArgumentException();
        if (args.o == null) {
            System.out.println(listOfFiles.toString(args));
        } else {
            BufferedWriter writer = new BufferedWriter(new FileWriter(args.o));
            writer.write(listOfFiles.toString(args));
            writer.close();
        }
    }
}

