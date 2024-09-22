package org.example.lab2;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

// #1.(16) Для заданной целочисленной матрицы найти минимум среди сумм модулей элементов диагоналей,
// параллельных побочной диагонали матрицы.
//Используется ArraysList

//Тест
//Введите n - размер матрицы n*n:
//        4
//        Вывод матрицы:
//        -46	-43	-47	29
//        -27	-47	43	43
//        -5	18	-43	30
//        25	44	-37	13
//        Минимальная сумма модулей элементов диагоналей: 13

public class Task1 {
    static Scanner scanner = new Scanner(System.in);

    public static void solve() {
        System.out.println("Введите n - размер матрицы n*n: ");
        int n = scanner.nextInt();

        ArrayList<ArrayList<Integer>> list1 = new ArrayList<>();
        Random random = new Random();

        System.out.println("Вывод матрицы:");
        for (int i = 0; i < n; i++) {
            ArrayList<Integer> str = new ArrayList<>();
            for (int j = 0; j < n; j++) {
                int num = random.nextInt(101) - 50; //заполнение от -50 до 50 (50-(-50)+1)
                str.add(num);
                System.out.print(num + "\t");
            }
            list1.add(str);
            System.out.println();
        }

// минимальная сумма модулей элементов диагоналей, параллельных побочной диагонали
        int minSum = Integer.MAX_VALUE;

// выше побочной
        for (int d = 1; d < n; d++) {

            int sum = 0;
            for (int i = 0; i < n; i++) {

                int j = n - 1 - d + i; // j - столбец
                if (j >= 0 && j < n) {
                    sum += Math.abs(list1.get(i).get(j));
                }
            }
            minSum = Math.min(minSum, sum);
        }

// ниже побочной
        for (int d = 1; d < n; d++) {
            int sum = 0;
            for (int i = d; i < n; i++) {

                int j = n - 1 - (i - d); // j - столбец
                if (j >= 0 && j < n) {
                    sum += Math.abs(list1.get(i).get(j));
                }
            }
            minSum = Math.min(minSum, sum);
        }

        System.out.println("Минимальная сумма модулей элементов диагоналей: " + minSum);

    }
}
