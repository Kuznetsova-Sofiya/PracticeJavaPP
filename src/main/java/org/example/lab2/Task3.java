package org.example.lab2;

import java.util.Map;
import java.util.HashMap;
import java.util.Random;
import java.util.Scanner;
import java.util.Vector;

//#3.(44) Найти максимальное из чисел, встречающихся в заданной матрице ровно два раза.
//Используется Vector

//Тест
//Введите n для матрицы n*m:
//        3
//        Введите n для матрицы n*m:
//        3
//        Вывод матрицы:
//        1	38	18
//        9	21	20
//        9	4	45
//        Максимальное число, встречающееся два раза: 9

public class Task3 {
    public static void solve() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Введите n для матрицы n*m: ");
        int n = scanner.nextInt();
        System.out.println("Введите m для матрицы n*m: ");
        int m = scanner.nextInt();

        Vector<Vector<Integer>> vec = new Vector<>();
        Random random = new Random();

        System.out.println("Вывод матрицы:");
        for (int i = 0; i < n; i++) {
            Vector<Integer> str = new Vector<>();
            for (int j = 0; j < m; j++) {
                int num = random.nextInt(50);
                str.add(num);
                System.out.print(num + "\t");
            }
            vec.add(str);
            System.out.println();
        }

        int maxNumber = findMaxNumber(vec);
        if (maxNumber != Integer.MIN_VALUE) {
            System.out.println("Максимальное число, встречающееся два раза: " + maxNumber);
        } else {
            System.out.println("Не найдено чисел, встречающихся два раза.");
        }
    }

    public static int findMaxNumber(Vector<Vector<Integer>> vec) {
        Map<Integer, Integer> countMap = new HashMap<>();

        // вхождения каждого числа
        for (Vector<Integer> row : vec) {
            for (Integer number : row) {
                countMap.put(number, countMap.getOrDefault(number, 0) + 1);
            }
        }

        int maxNumber = Integer.MIN_VALUE;
        boolean found = false;

        // Поиск максимального
        for (Map.Entry<Integer, Integer> entry : countMap.entrySet()) {
            if (entry.getValue() == 2) {
                found = true;
                maxNumber = Math.max(maxNumber, entry.getKey());
            }
        }

        return found ? maxNumber : Integer.MIN_VALUE;

    }
}
