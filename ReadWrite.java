import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;
import java.util.SortedSet;
import java.util.TreeSet;

public class ReadWrite {
    public static SortedSet parsing(String textCollection) throws IOException {
        //чтение файла и возврат заполненной коллекции
        SortedSet<String> trySet = new TreeSet<>();

        //Path path = Paths.get(textCollection);
        try (Scanner scanner = new Scanner(textCollection)) {
            //читаем построчно
            while (scanner.hasNext()) {
                String line = scanner.next();
                line = line.replace(",", "");
                line = line.replace(".", "");
                line = line.replace("?", "");
                line = line.replace("!", "");
                line = line.replace(":", "");
                line = line.replace(";", "");
                line = line.replace("-", "");
                line = line.replace("*", "");
                line = line.replace("\"", "");
                line = line.replace("(", "");
                line = line.replace(")", "");
                line = line.replace("“", "");
                line = line.replace("”", "");
                line = line.replace("’", "'");


                if (line.length() > 2)
                    trySet.add(line);
            }
        }
        return (trySet);
    }


    public static SortedSet readUsingScanner(String fileName) throws IOException {
        //чтение файла и возврат заполненной коллекции
        SortedSet<String> trySet = new TreeSet<>();

        Path path = Paths.get(fileName);
        try (Scanner scanner = new Scanner(path)) {
            //читаем построчно
            while (scanner.hasNext()) {
                String line = scanner.next();
                line = line.replace(",", "");
                line = line.replace(".", "");
                line = line.replace("?", "");
                line = line.replace("!", "");
                line = line.replace(":", "");
                line = line.replace(";", "");
                line = line.replace("-", "");
                line = line.replace("–", "");
                line = line.replace("—", "");
                line = line.replace("*", "");
                line = line.replace("“", "");
                line = line.replace("”", "");
                line = line.replace("’", "'");


                if (line.length() > 2)
                    trySet.add(line);
            }
        }
        return (trySet);
    }

    /// запись данных в словарь
    public static void writeFile(String fileName, SortedSet<String> trySet) {
        File file = new File(fileName);
        FileWriter fr = null;

        /// запись в файл из коллекции
        try {
            fr = new FileWriter(file, false);
            for (String s : trySet) {
                fr.write(s + "\r\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                assert fr != null;
                fr.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


}
