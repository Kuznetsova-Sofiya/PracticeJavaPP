/* Кузнецова София 2 курс лр4 java #2

Постановка задачи
Для выполнения заданий использовать регулярные выражения.
Каждое задание реализовать в отдельном методе.
Строку получать из текстового файла input.txt.
Результат работы методов записывать в выходной текстовый файл output.txt.

    1. Из заданной строки исключить символы, расположенные внутри круглых скобок. Сами скобки тоже должны быть исключены.
    2. Из заданной строки удалить из каждой группы идущих подряд цифр, в которой более двух цифр, все цифры, начиная с третьей.
    3. Из заданной строки удалить из каждой группы идущих подряд цифр все незначащие нули.

*/

package org.example.lab4;

import java.io.*;
import java.nio.file.*;
import java.util.regex.*;

public class Task2 {
    public static void main(String[] args) {
        String inputFileName = "D:\\Study\\Java\\PracticeJavaPP\\src\\main\\java\\org\\example\\lab4\\input.txt";
        String outputFileName = "D:\\Study\\Java\\PracticeJavaPP\\src\\main\\java\\org\\example\\lab4\\output.txt";

        try {
            // Считать строку из файла input.txt
            String input = new String(Files.readAllBytes(Paths.get(inputFileName)));

            // Обработка по заданиям
            String result1 = removeTextInParentheses(input);
            String result2 = truncateDigitsAfterSecond(result1);
            String result3 = removeLeadingZeros(result2);

            // Запись результата в файл output.txt
            Files.write(Paths.get(outputFileName), result3.getBytes());
            System.out.println("Обработка завершена, результат записан в файл " + outputFileName);
        } catch (IOException e) {
            System.err.println("Ошибка при чтении или записи файла: " + e.getMessage());
        }
    }

    // Метод 1: Удалить текст в круглых скобках вместе с самими скобками
    private static String removeTextInParentheses(String input) {
        return input.replaceAll("\\([^)]*\\)", "");
    }

    // Метод 2: Удалить все цифры, начиная с третьей, в каждой группе подряд идущих цифр
    private static String truncateDigitsAfterSecond(String input) {
        return input.replaceAll("(\\d{2})\\d+", "$1");
    }

    // Метод 3: Удалить ведущие нули в каждой группе цифр
    private static String removeLeadingZeros(String input) {
        return input.replaceAll("\\b0+(\\d+)", "$1");
    }
}
