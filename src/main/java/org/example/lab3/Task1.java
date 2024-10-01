package org.example.lab3;

/*
//Кузнецова София 2 курс лр3 java #1
(использовать  String, ,  StringBuilder и  StringBuffer и StringTokenizer)

7.	Словом в строке считается последовательность букв латинского алфавита, остальные символы
рассматриваются как  разделители между словами. Слова в каждой из двух исходных строк упорядочены по алфавиту.
Необходимо сформировать новую строку из всех слов исходных строк. Слова в новой строке должны быть также
упорядочены по алфавиту. Прописные и строчные буквы в словах не различать. Слова в исходной строке разделяются
некоторым множеством разделителей. Слова в новой строке должны разделяться ровно одним пробелом.

Тест:
Введите 1 строку:
a, b, e, f g, fjjf
Введите 2 строку:
a, c, d , kkk
Результат: a b c d e f fjjf g kkk

*/

import java.util.Scanner;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class Task1 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите 1 строку: ");
        String str1 = scanner.nextLine();
        System.out.println("Введите 2 строку: ");
        String str2 = scanner.nextLine();

        String result = mergeAndSortWords(str1, str2);
        System.out.println("Результат: " + result);
    }

    public static String mergeAndSortWords(String str1, String str2) {

        Set<String> uniqueWords = new TreeSet<>(String.CASE_INSENSITIVE_ORDER);

        extractWords(str1, uniqueWords);
        extractWords(str2, uniqueWords);

        // Используем StringBuilder для формирования финальной строки
        StringBuilder resultBuilder = new StringBuilder();
        for (String word : uniqueWords) {
            resultBuilder.append(word).append(" ");
        }

        /*
        Используем StringBuffer для формирования финальной строки
        StringBuffer resultBuffer = new StringBuffer();
        for (String word : uniqueWords) {
            resultBuffer.append(word).append(" ");
        }
        */


        // Удаляем последний пробел, если он есть
        if (resultBuilder.length() > 0) {
            resultBuilder.setLength(resultBuilder.length() - 1);
        }
        return resultBuilder.toString();
    }

    private static void extractWords(String str, Set<String> words) {
        StringTokenizer tokenizer = new StringTokenizer(str, " ,.;:!?-()[]{}\"'\n\t\r");
        while (tokenizer.hasMoreTokens()) {
            words.add(tokenizer.nextToken());
        }
    }

}



