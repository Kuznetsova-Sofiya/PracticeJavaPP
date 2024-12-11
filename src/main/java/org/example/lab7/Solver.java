package org.example.lab7;

/* Лаб7 Кузнецовой Софии 2 курс 5 группа

Условие:
Поток Main должен выполнить следующие действия:
•	создать массив целых чисел, размерность вводится пользователем с клавиатуры, элементы которого задаются программно числами от 1 до размерности;
•	вывести размерность и элементы исходного массива на консоль;
•	запросить у пользователя порядок сортировки массива (возрастание или убывание);
•	запустить поток Sort;
•	вывести на консоль элементы отсортированного массива после завершения работы потока Sort.

Поток Sort должен выполнить следующие действия:
•	отсортировать элементы введенного массива в соответствии с заданным порядком (использовать Comparator).

Алгоритм:
1. Инициализация
2. Ввод размера массива
3. Создание и заполнение массива
4. Вывод исходного массива
5. Ввод порядка сортировки
6. Создание и запуск потока сортировки
7. Ожидание завершения сортировки
8. Вывод отсортированного массива
9. Обработка ошибок
10. Завершение работы

Тест1:
Введите размер массива: 6
Исходный массив: [1, 2, 3, 4, 5, 6]
Введите порядок сортировки (1 для возрастания, 2 для убывания): 1
Отсортированный массив: [1, 2, 3, 4, 5, 6]

Тест2:
Введите размер массива: 4
Исходный массив: [1, 2, 3, 4]
Введите порядок сортировки (1 для возрастания, 2 для убывания): 2
Отсортированный массив: [4, 3, 2, 1]

Тест3:
Введите размер массива: 8
Исходный массив: [1, 2, 3, 4, 5, 6, 7, 8]
Введите порядок сортировки (1 для возрастания, 2 для убывания): 0
Ошибка: Введен некорректный порядок сортировки. Используйте 1 для возрастания или 2 для убывания.


 */

import java.util.Arrays;
import java.util.Comparator;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Solver {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        try {
            System.out.print("Введите размер массива: ");
            int size = scanner.nextInt();

            if (size <= 0) {
                throw new IllegalArgumentException("Размер массива должен быть положительным числом.");
            }

            int[] array = new int[size];
            for (int i = 0; i < size; i++) {
                array[i] = i + 1;
            }

            System.out.println("Исходный массив: " + Arrays.toString(array));

            System.out.print("Введите порядок сортировки (1 для возрастания, 2 для убывания): ");
            int sortOrder = scanner.nextInt();

            if (sortOrder != 1 && sortOrder != 2) {
                throw new IllegalArgumentException("Введен некорректный порядок сортировки. Используйте 1 для возрастания или 2 для убывания.");
            }

            boolean ascending = (sortOrder == 1);

            // Создание потока сортировки
            SortThread sortThread = new SortThread(array, ascending);
            sortThread.start();

            // Ожидание завершения потока Sort
            try {
                sortThread.join();
            } catch (InterruptedException e) {
                System.out.println("Главный поток был прерван.");
            }

            System.out.println("Отсортированный массив: " + Arrays.toString(array));
        } catch (InputMismatchException e) {
            System.out.println("Ошибка ввода: пожалуйста, вводите только числа.");
        } catch (IllegalArgumentException e) {
            System.out.println("Ошибка: " + e.getMessage());
        } finally {
            scanner.close();
        }
    }

    static class SortThread extends Thread {
        private final int[] array;
        private final boolean ascending;

        public SortThread(int[] array, boolean ascending) {
            this.array = array;
            this.ascending = ascending;
        }

        @Override
        public void run() {
            // Сортировка массива с использованием Comparator
            Integer[] boxedArray = Arrays.stream(array).boxed().toArray(Integer[]::new);
            Arrays.sort(boxedArray, ascending ? Comparator.naturalOrder() : Comparator.reverseOrder());

            for (int i = 0; i < array.length; i++) {
                array[i] = boxedArray[i];
            }
        }
    }
}
