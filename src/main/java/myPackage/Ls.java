package myPackage;

import org.kohsuke.args4j.CmdLineException;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.FileTime;
import java.text.SimpleDateFormat;
import java.util.*;

public class Ls {
    public static void main(String[] args) throws CmdLineException, IOException {
        LsArgs values = new LsArgs(args);//обрабатываем аргументы из консоли
        File folder = new File(values.getDirectory()); //директори или файл(название геттера деректории из того файла)
        File[] list;//список файлов в дериктории

        if (folder.isFile()) { //проверяем на то, файл это или папка
            list = new File[]{folder}; //массив с типом File,который состоит из 1 аргумента
        } else {
            list = folder.listFiles(); //получаю из папки список файлов
        }

        if (values.isReversed()) {
            List<File> reversed = Arrays.asList(list);//массиы делается List
            Collections.reverse(reversed);//переворачиваем список
            list = reversed.toArray(list);//обратно делаем из списка массив
        }
        String result = "";
        if (!values.isLong()) {
            result = getNames(list);//если не -l то просто выводим список имен
        } else {
            if (!values.isHumanReadble()) {
                result = getLongInfo(list);
            } else {
                result = getHumanReadableInfo(list);
            }
        }

        File output = values.getOutputFile();
        if (output != null) {
            FileWriter writer = new FileWriter(output);
            writer.write(result);
            writer.close();
        } else {
            System.out.print(result);//вывод в консоль если нет файла для вывода
        }
    }

    private static String getNames(File[] list) {
        StringBuilder sb = new StringBuilder();
        for (File file : list) {
            sb.append(file.getName() + System.lineSeparator()); //добавляем имя файла и перенос строки
        }
        return sb.toString();
    }

    private static String getLongInfo(File[] list) throws IOException {
        StringBuilder sb = new StringBuilder();
        for (File file : list) {
            if (file.canRead()) sb.append(1);
            else sb.append(0);

            if (file.canWrite()) sb.append(1);
            else sb.append(0);

            if (file.canExecute()) sb.append(1);
            else sb.append(0);

            sb.append(" ");
            Path path = Path.of(file.getAbsolutePath());//путь к файлу
            sb.append(Files.size(path));//выводим размер файла в байтах
            sb.append(" ");
            sb.append(Files.getLastModifiedTime(path));//время и дата последнего изменения
            sb.append(" ");
            sb.append(file.getName() + System.lineSeparator()); //добавляем имя файла и перенос строки
        }
        return sb.toString();
    }

    private static String getHumanReadableInfo(File[] list) throws IOException {
        StringBuilder sb = new StringBuilder();
        for (File file : list) {
            if (file.canRead()) sb.append("r");
            else sb.append("-");

            if (file.canWrite()) sb.append("w");
            else sb.append("-");

            if (file.canExecute()) sb.append("x");
            else sb.append("-");

            sb.append(" ");
            Path path = Path.of(file.getAbsolutePath());//путь к файлу
            long size = Files.size(path); // получаем размер файла
            sb.append(sizeForHuman(size));//выводим размер файла в удобном виде
            sb.append(" ");
            FileTime time = Files.getLastModifiedTime(path);
            sb.append(timeForHuman(time));//время и дата последнего изменения
            sb.append(" ");
            sb.append(file.getName() + System.lineSeparator()); //добавляем имя файла и перенос строки
        }
        return sb.toString();
    }


    private static String timeForHuman(FileTime time) {
        Date date = new Date(time.toMillis());
        String pattern = "d-MM-y";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        return simpleDateFormat.format(date);
    }

    private static String sizeForHuman(long size) {
        String[] namesList = new String[] {"б", "Кб", "Мб", "Гб", "Тб"};
        long multiplier = 1024;
        int index = 0;
        long currentSize = size;
        while (currentSize/multiplier > 0) {
            index++;
            currentSize = currentSize / multiplier;
        }
        if (index >= namesList.length) return "Too big size";
        return currentSize + namesList[index];
    }
}
