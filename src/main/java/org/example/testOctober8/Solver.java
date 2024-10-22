package org.example.testOctober8;
/*
Предприятие покупает станки. Дано: массив стоимостей S, массив мощностей каждого станка M и общее количество станков в данном массиве К
Также максимальное количество станков, которое может купить предприятие - KL (в зависимости от максимальной эффективности их может быть меньше) и общая мощность всех станков MN (если нет такого варианта, то общая мощность может быть < введенного числа).
Количество купленных станков обязательно должно быть четным, расстановить станки по мощности, но палиндромом(по бокам с минимальной мощностью, в центре с максимальной мощностью)
Определить оптимальное количество станков, для каждого из них, начиная с новой строки, вывести их характеристики и расстановку, и ,последней строкой, общую стоимость покупки.
Данные вводятся с клавиатуры.

Тест:
Количество различных станков K: 5
Массив стоимости станков:
10.0 12.0 8.0 3.0 20.0
Массив мощности станков :
80 100 70 30 150
Введите максимальное количество станков KL:
4
Введите необходимую мощность MN:
280
Оптимальное количество станков: 4
Характеристики станков (мощность может быть меньше введенной):
30 70 70 30
Общая стоимость покупки: 33.0


 */

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Solver {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int K = 5;
        System.out.println("Количество различных станков K: " + K);

        List<Double> costs = new ArrayList<>();
        Collections.addAll(costs, 10.0, 12.0, 8.0, 3.0, 20.0);
        List<Integer> powers = new ArrayList<>();
        Collections.addAll(powers, 80, 100, 70, 30, 150);

        System.out.println("Массив стоимости станков: ");
        for (int i = 0; i < K; i++) {
            System.out.print(costs.get(i) + " ");
        }
        System.out.println();

        System.out.println("Массив мощности станков : ");
        for (int i = 0; i < K; i++) {
            System.out.print(powers.get(i) + " ");
        }
        System.out.println();

        System.out.println("Введите максимальное количество станков KL: ");
        int KL = scanner.nextInt();

        System.out.println("Введите необходимую мощность MN: ");
        int MN = scanner.nextInt();

        // Нахождение оптимального варианта

        List<Integer> selectedPowers = new ArrayList<>();
        List<Double> selectedCosts = new ArrayList<>();
        int totalPower = 0;

        /*
       for (int i = 0; i < K - 1; i++) {
            for (int j = 0; j < K - i - 1; j++) {
                if (powers.get(j) > powers.get(j + 1)) {

                    int temp2 = powers.get(j);
                    powers.set(j, powers.get(j + 1));
                    powers.set(j + 1, temp2);

                    double temp1 = costs.get(j);
                    costs.set(j, costs.get(j + 1));
                    costs.set(j + 1, temp1);
                }
            }
*/


        for (int i = 0; i < K; i++) {
            if (totalPower < MN && selectedPowers.size() < KL) {
                selectedPowers.add(powers.get(i));
                selectedCosts.add(costs.get(i));
                totalPower += powers.get(i);
            }
        }

        // Проверка на четность
        if(selectedPowers.size()%2!=0)

        {
            selectedPowers.remove(selectedPowers.size() - 1);
            selectedCosts.remove(selectedCosts.size() - 1);
        }

        // Расстановка палиндромном
        Collections.sort(selectedPowers);
        Collections.sort(selectedCosts);

        List<Integer> arrangement = new ArrayList<>();
        for(
                int i = 0; i<selectedPowers.size()/2;i++)

        {
            arrangement.add(selectedPowers.get(i));
        }
        for(
                int i = selectedPowers.size() / 2 - 1;
                i >=0;i--)

        {
            arrangement.add(selectedPowers.get(i));
        }

        // Общая стоимость
        double totalCost = 0;
        for(
                Double cost :selectedCosts)

        {
            totalCost += cost;
        }

        // Вывод результатов
        System.out.println("Оптимальное количество станков: "+selectedPowers.size());
        System.out.println("Характеристики станков (мощность может быть меньше введенной): ");
        for(
                Integer power :arrangement)

        {
            System.out.print(power + " ");
        }
        System.out.println();
        System.out.println("Общая стоимость покупки: "+totalCost);
    }
}
