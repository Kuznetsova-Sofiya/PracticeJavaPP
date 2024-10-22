/* Кузнецова София 2 курс лр4 java #1

Входные данные:
Входной файл input1.html содержит текст, написанный на языке HTML.
В тесте находятся теги. В одной строке может быть несколько тегов. Теги находятся в угловых скобках, каждому открывающему тегу ставится в соответствие закрывающий тег. Например, пара тегов<b></b>.
Между тегами находится текст, причем теги не разрывают текст. Например, при поиске слова hello комбинация h<b><i>el</i>l</b>o должна быть найдена.
Гарантируется,что страница HTML является корректной, т.е. все символы "<" и ">" используются только в тегах, все теги записаны корректно.
Входной файл input2.in содержит список фрагментов текста, которые нужно найти в первом файле, записанных через разделители (точка с запятой). Может быть несколько строк.

Примечание: Ваша программа должна игнорировать различие между строчными и прописными буквами и для тегов и для искомого контекста.

Выходные данные:
1. В выходной файл output1.out вывести список всех тегов в порядке возрастания количества символов тега.
2. В выходной файл output2.out вывести номера строк (нумерация с 0) первого файла, в которых был найден искомый контекст в первый раз или -1 , если не был найден.
3. В выходной файл output3.out - список фрагментов второго файла, которые НЕ были найдены в первом файле.


*/

package org.example.lab4;

import java.io.*;
import java.nio.file.*;
import java.util.*;
import java.util.regex.*;


public class Task1 {

    public static void main(String[] args) {
        String inputHtmlFile = "D:\\Study\\Java\\PracticeJavaPP\\src\\main\\java\\org\\example\\lab4\\input1.html.txt";
        String inputFragmentsFile = "D:\\Study\\Java\\PracticeJavaPP\\src\\main\\java\\org\\example\\lab4\\input2.in";

        List<String> htmlLines = readFile(inputHtmlFile);
        List<String> fragments = readFile(inputFragmentsFile);

        if (htmlLines.isEmpty() || fragments.isEmpty()) {
            System.err.println("Ошибка: один или оба входных файла пусты или отсутствуют.");
            return;
        }

        List<String> tags = extractTags(htmlLines);
        List<String> textLines = extractTextLines(htmlLines);

        saveTagsToFile(tags, "D:\\Study\\Java\\PracticeJavaPP\\src\\main\\java\\org\\example\\lab4\\output1.out.txt");
        saveFragmentSearchResults(fragments, textLines, "D:\\Study\\Java\\PracticeJavaPP\\src\\main\\java\\org\\example\\lab4\\output2.out.txt");
        saveUnfoundFragments(fragments, textLines, "D:\\Study\\Java\\PracticeJavaPP\\src\\main\\java\\org\\example\\lab4\\output3.out.txt");
    }

    private static List<String> readFile(String fileName) {
        try {
            return Files.readAllLines(Paths.get(fileName));
        } catch (NoSuchFileException e) {
            System.err.println("Ошибка: файл " + fileName + " не найден.");
        } catch (IOException e) {
            System.err.println("Ошибка чтения файла " + fileName + ": " + e.getMessage());
        }
        return Collections.emptyList();
    }

    private static List<String> extractTags(List<String> htmlLines) {
        List<String> tags = new ArrayList<>();
        Pattern tagPattern = Pattern.compile("<[^>]+>");
        for (String line : htmlLines) {
            Matcher matcher = tagPattern.matcher(line.toLowerCase());
            while (matcher.find()) {
                tags.add(matcher.group());
            }
        }
        tags.sort(Comparator.comparingInt(String::length));
        return tags;
    }

    private static List<String> extractTextLines(List<String> htmlLines) {
        List<String> textLines = new ArrayList<>();
        Pattern tagPattern = Pattern.compile("<[^>]+>");

        for (String line : htmlLines) {
            String text = tagPattern.matcher(line.toLowerCase()).replaceAll("");
            textLines.add(text);
        }

        return textLines;
    }

    private static void saveTagsToFile(List<String> tags, String fileName) {
        try (BufferedWriter writer = Files.newBufferedWriter(Paths.get(fileName))) {
            for (String tag : tags) {
                writer.write(tag);
                writer.newLine();
            }
        } catch (IOException e) {
            System.err.println("Ошибка записи в файл " + fileName + ": " + e.getMessage());
        }
    }

    private static void saveFragmentSearchResults(List<String> fragments, List<String> textLines, String fileName) {
        try (BufferedWriter writer = Files.newBufferedWriter(Paths.get(fileName))) {
            for (String fragment : fragments) {
                int lineIndex = findFragmentInLines(fragment.toLowerCase(), textLines);
                writer.write(Integer.toString(lineIndex));
                writer.newLine();
            }
        } catch (IOException e) {
            System.err.println("Ошибка записи в файл " + fileName + ": " + e.getMessage());
        }
    }

    private static int findFragmentInLines(String fragment, List<String> textLines) {
        for (int i = 0; i < textLines.size(); i++) {
            if (textLines.get(i).contains(fragment)) {
                return i;
            }
        }
        return -1;
    }

    private static void saveUnfoundFragments(List<String> fragments, List<String> textLines, String fileName) {
        try (BufferedWriter writer = Files.newBufferedWriter(Paths.get(fileName))) {
            for (String fragment : fragments) {
                if (findFragmentInLines(fragment.toLowerCase(), textLines) == -1) {
                    writer.write(fragment);
                    writer.newLine();
                }
            }
        } catch (IOException e) {
            System.err.println("Ошибка записи в файл " + fileName + ": " + e.getMessage());
        }
    }
}
