package org.example.lab3;

/*
//Кузнецова София 2 курс лр3 java #2
(использовать  String, ,  StringBuilder и  StringBuffer и StringTokenizer)

7.	Задан текстовый файл input.txt. Требуется определить строки этого файла, содержащие
максимальную по длине подстроку, состоящую из одинаковых символов русского алфавита.
Заглавные и строчные буквы не различаются. Если таких строк несколько, найти первые 10.
Результат вывести на консоль в форме, удобной для чтения.

Тест:
Строки с максимальной подстрокой длиной 5:
Это пример строки с несколькими одинаковыми буквами: ааа, бб, ккккк.
Тестирование строк: а, ааа, бб, ббб, бббб, ббббб.

*/



import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Task2 {
    public static void main(String[] args) {
        String fileName = "D:\\Study\\Java\\PracticeJavaPP\\src\\main\\java\\org\\example\\lab3\\input.txt";
        List<String> resultLines = new ArrayList<>();
        int maxLength = 0;

        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = br.readLine()) != null) {
                int currentMaxLength = findMaxRepeatedLength(line);

                if (currentMaxLength > maxLength) {
                    maxLength = currentMaxLength;
                    resultLines.clear();
                    resultLines.add(line);
                } else if (currentMaxLength == maxLength && currentMaxLength > 0) {
                    if (resultLines.size() < 10) {
                        resultLines.add(line);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        printResults(resultLines, maxLength);
    }

    private static int findMaxRepeatedLength(String line) {
        StringBuilder sb = new StringBuilder();
        line = line.toLowerCase(); // Приводим к нижнему регистру

        char prevChar = '\0';
        int currentLength = 1;
        int maxLength = 1;

        for (char ch : line.toCharArray()) {
            if (ch == prevChar) {
                currentLength++;
            } else {
                maxLength = Math.max(maxLength, currentLength);
                currentLength = 1;
            }
            prevChar = ch;
        }
        maxLength = Math.max(maxLength, currentLength); // Проверяем последний сегмент

        return maxLength;
    }

    private static void printResults(List<String> lines, int maxLength) {
        System.out.println("Строки с максимальной подстрокой длиной " + maxLength + ":");
        for (String line : lines) {
            System.out.println(line);
        }
    }

}