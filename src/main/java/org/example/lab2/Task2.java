package org.example.lab2;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Random;
import java.util.Scanner;

// #2.(30) Упорядочить столбцы матрицы по возрастанию их наименьших элементов.
// Пример: в матрица 3*2, в первом столбце наименьший элемент 4, во втором 2, в третьем 7,
// столбцы должны быть отсортированы как 2 4 7
//Используется Arrays

//Тест
//Введите n для матрицы n*n:
//        3
//        Вывод изначальной матрицы:
//        1	7	8
//        1	6	8
//        2	5	4
//        Матрица после сортировки:
//        [1, 8, 7]
//        [1, 8, 6]
//        [2, 4, 5]

public class Task2 {
    public static void solve() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Введите n для матрицы n*n: ");
        int n = scanner.nextInt();

        int[][] mas = new int[n][n];
        Random random = new Random();

        System.out.println("Вывод изначальной матрицы:");
        for(int i = 0; i< n; i++){
            for(int j = 0; j< n; j++) {

                mas[i][j] = random.nextInt(10); //заполнение от 0 до 9
                System.out.print(mas[i][j] + "\t");
            }
            System.out.println();
        }

        // Сортировка столбцов по наименьшим элементам
        int[][] sortMas = sortMinEl(mas, n);

        System.out.println("Матрица после сортировки:");
        printMatrix(sortMas);
    }

    public static int[][] sortMinEl(int[][] mas, int n) {

        Integer[] indices = new Integer[n];
        for (int i = 0; i < n; i++) {
            indices[i] = i;
        }

        // Сортируем индексы по наименьшим
        Arrays.sort(indices, Comparator.comparingInt(a -> getMinElement(mas, a)));


        // новая отсортированная матрица
        int[][] sortMas = new int[n][n];
        for (int i = 0; i < n; i++) {
            int colIndex = indices[i];
            for (int j = 0; j < n; j++) {
                sortMas[j][i] = mas[j][colIndex];
            }
        }

        return sortMas;
    }

    // наименьший элемент столбца
    public static int getMinElement(int[][] mas, int colIndex) {
        int min = Integer.MAX_VALUE;
        for (int[] n : mas) {
            if (n[colIndex] < min) {
                min = n[colIndex];
            }
        }
        return min;
    }
    //вывод
    public static void printMatrix(int[][] mas) {
        for (int[] n : mas) {
            System.out.println(Arrays.toString(n));
        }
    }
}
