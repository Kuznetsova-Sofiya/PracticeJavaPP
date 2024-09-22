package org.example.lab1;

import java.math.BigDecimal;
import java.util.Scanner;

//Кузнецова София 2 курс лр1 java
//решение 2ух задач:
//сравнение стандартной функции косинуса с моей функцией, черз double
//сравнение стандартной функции косинуса с моей функцией, черз BigDecimal


public class Solver1 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Введите k: ");
        int k = scanner.nextInt();

        MyCos myCos = new MyCos();
        myCos.setEpsilon(k);

        MyBigCos myBigCos = new MyBigCos();
        myBigCos.setEpsilon(k);

        System.out.println("Введите x: ");
        double x = scanner.nextDouble();

        System.out.println("Результат через double: ");
        ResultCompare.resView(x, myCos.getEpsilon(), myCos.calculation(x), Math.cos(x));

        System.out.println("\n\nРезультат через BigDecimal: ");
        ResultCompare.resBigView(x, myBigCos.getEpsilon(), myBigCos.calculation(new BigDecimal(x)), Math.cos(x));

    }
}
