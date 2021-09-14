package rent;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class MyUtils {

    //Чтение тестовых данных из файла (filename)
    public static ArrayList<String> readFileLines(String filename) throws IOException {
        ArrayList<String> result = new ArrayList<>();
        List<String> result1;
        int i;
        String line;
        int numStrok;//Число строк в файле
        int num;//Индекс разделителя "QQ" в строке
        String delimiter = "QQ";
        try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(new FileInputStream(new File(filename)), StandardCharsets.UTF_8))) {
            result1 = reader.lines().collect(Collectors.toList());
            numStrok = result1.size();
            for (i = 0; i < numStrok; i++) {
                num = result1.get(i).indexOf(delimiter);
                String line1 = result1.get(i);
                line = line1.substring(0, num).replace("[^a-zA-Zа-яА-Я0-9\\s\\/\\:\\,\\.]", "").trim();
                result.add(line);
            }
        }
        return result;
    }

    //Генерация случайной строки
    public static String generateString(Random random, String characters, int length)
    {
        char[] text = new char[length];
        for (int i = 0; i < length; i++)
        {
            text[i] = characters.charAt(random.nextInt(characters.length()));
        }
        return new String(text);
    }

}
