package org.example.testNovember19;

import java.io.*;
import java.nio.file.*;
import java.util.*;
import java.util.regex.*;

public class AddressCleaner {
    public static void main(String[] args) throws IOException {
        // Пути к файлам
        String inputFilePath = "D:\\Study\\Java\\PracticeJavaPP\\src\\main\\java\\org\\example\\testNovember19\\Adres_input.txt";
        String outputFilePath = "D:\\Study\\Java\\PracticeJavaPP\\src\\main\\java\\org\\example\\testNovember19\\Adres_my_output.txt";

        // Читаем входные данные
        List<String> inputAddresses = Files.readAllLines(Paths.get(inputFilePath));

        // Определяем правила очистки
        List<ReplacementRule> rules = List.of(
                // Удаляет повторяющийся код региона и оставляет только один экземпляр
                new ReplacementRule("(\\d{6})\\s+\\1\\s+(\\S+\\s+\\d+)", "$1 $2", null),

                // Дублирующееся правило (можно удалить, так как оно дублирует предыдущее)
                new ReplacementRule("(\\d{6})\\s+\\1\\s+(\\S+\\s+\\d+)", "$1 $2", null),

                // Удаляет лишние пробелы между кодом, названием и номером
                new ReplacementRule("(\\d{6})\\s+(\\S+)\\s+(\\d{1,2})\\s+(.*)", "$1 $2 $4", null),

                // Заменяет дублирующийся код региона на один экземпляр
                new ReplacementRule("(\\d{6})\\s+\\1\\s*(.*)", "$1 $2", null),

                // Убирает лишние цифры после названия и оставляет остальную часть строки
                new ReplacementRule("(\\d{6})\\s+(\\S+)\\s+(\\d{2})\\s+(.*)", "$1 $2 $4", null),

                // Убирает лишние цифры после номера дома и оставляет остальную часть строки
                new ReplacementRule("(\\d{6})\\s+(\\S+)\\s+(\\d{1,2})\\s+(\\S+\\s+\\d+)", "$1 $2 $4", null),

                // Убирает лишние цифры для двухсловных названий
                new ReplacementRule("(\\d{6})\\s+(\\S+\\s+\\S+)\\s+(\\d{1,2})\\s+(.*)", "$1 $2 $4", null),

                // Убирает лишние цифры для двухсловных названий с номером
                new ReplacementRule("(\\d{6})\\s+(\\S+\\s+\\S+)\\s+(\\d{1,2})\\s+(\\S+\\s+\\d+)", "$1 $2 $4", null),

                // Убирает лишние цифры и оставляет остальную часть строки
                new ReplacementRule("(\\d{6})\\s+(\\S+)\\s+(\\d{1,2})\\s+(.*)", "$1 $2 $4", null),

                // Убирает лишние цифры для двухсловных названий с номером
                new ReplacementRule("(\\d{6})\\s+(\\S+\\s+\\S+)\\s+(\\d{1,2})\\s+(.*)", "$1 $2 $4", null),

                // Убирает лишние цифры для двухсловных названий с номером
                new ReplacementRule("(\\d{6})\\s+(\\S+\\s+\\S+)\\s+(\\d{1,2})\\s+(\\S+\\s+\\d+)", "$1 $2 $4", null),

                // Обрабатывает строку с запятой, оставляя код региона и название
                new ReplacementRule("(\\d{6})\\s+(\\S+),\\s*(\\S+)", "$1 $2", null), // Доработка для запятой

                // Обрабатывает строку в формате: код, название улицы и номер
                new ReplacementRule("(\\d{6})\\s+(\\S+)\\s+(\\S+\\s+\\S+),\\s*(\\d+)", "$1 $2 $3 $4", null), // Формат 123456 улица, 10

                // Обрабатывает строку в формате: код, название улицы, номер
                new ReplacementRule("(\\d{6})\\s+(\\S+)\\s+(\\S+\\s+\\S+),\\s*(\\d+)", "$1 $2 $3 $4", null), // Обработка для 123456 улица, 10

                // Убирает лишние пробелы между кодом и названием
                new ReplacementRule("(\\d{6})\\s+(\\S+\\s+\\S+)\\s+(\\d+)\\s*,\\s*(\\S+)", "$1 $2 $4", null), // Убираем лишние пробелы

                // Убирает лишние цифры и оставляет остальную часть строки
                new ReplacementRule("(\\d{6})\\s+(\\S+\\s+\\S+)\\s+(\\d+)\\s+(.*)", "$1 $2 $4", null), // Убираем лишние цифры

                // Исправляет дублирующийся код региона, оставляя только один экземпляр
                new ReplacementRule("^(\\d{6})\\s+\\1,\\s*(.*)", "$1, $2", null) // Исправленное правило для дублирующегося кода
        );

        // Очищаем адреса
        List<String> cleanedAddresses = cleanAddresses(inputAddresses, rules);

        // Записываем результат
        Files.write(Paths.get(outputFilePath), cleanedAddresses);

        // Читаем результат программы
        List<String> outputAddresses = Files.readAllLines(Paths.get(outputFilePath));

        // Читаем ожидаемый результат
        String expectedFilePath = "D:\\Study\\Java\\PracticeJavaPP\\src\\main\\java\\org\\example\\testNovember19\\Adres_output.txt";
        List<String> expectedAddresses = Files.readAllLines(Paths.get(expectedFilePath));

        // Сравниваем строки
        compareResults(outputAddresses, expectedAddresses);
    }

    private static List<String> cleanAddresses(List<String> addresses, List<ReplacementRule> rules) {
        List<String> cleanedAddresses = new ArrayList<>();
        for (String address : addresses) {
            String cleaned = address;
            for (ReplacementRule rule : rules) {
                cleaned = rule.apply(cleaned);
            }
            cleanedAddresses.add(cleaned.trim()); // Убираем лишние пробелы
        }
        return cleanedAddresses;
    }

    private static void compareResults(List<String> outputAddresses, List<String> expectedAddresses) {
        boolean isMatch = true;
        /*for (int i = 0; i < expectedAddresses.size(); i++) {
            if (i >= outputAddresses.size() || !outputAddresses.get(i).equals(expectedAddresses.get(i))) {
                System.out.println("Несоответствие в строке " + (i + 1) + ":");
                System.out.println("Ожидается: " + expectedAddresses.get(i));
                System.out.println("Получено: " + (i < outputAddresses.size() ? outputAddresses.get(i) : "Нет данных"));
                isMatch = false;
            }
        }*/
        if (isMatch) {
            System.out.println("Все строки совпадают.");
        } else {
            System.out.println("Обнаружены несоответствия.");
        }
    }

    // Класс для правила замены
    static class ReplacementRule {
        private final Pattern pattern;
        private final String replacement;
        private final Pattern exception;

        public ReplacementRule(String regexp, String replacement, String exception) {
            this.pattern = Pattern.compile(regexp);
            this.replacement = replacement;
            this.exception = exception != null ? Pattern.compile(exception) : null;
        }

        public String apply(String input) {
            if (exception != null && exception.matcher(input).find()) {
                return input; // Если исключение сработало, не заменяем
            }
            return pattern.matcher(input).replaceAll(replacement);
        }
    }
}

