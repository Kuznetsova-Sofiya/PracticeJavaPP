package org.example.lab1;

import java.math.BigDecimal;

public class ResultCompare {
    public static void resView(double x, double epsilon, double myRes, double javaRes){

        double sub = Math.abs(myRes - javaRes);

        System.out.println("точность: " + epsilon + "\nx: " + x + "\nмоя функция: " +  String.format("%.3f", myRes) +
                "\nстандартная функция: " + String.format("%.3f", javaRes) + "\nпогрешноссть вычисления: "
                + String.format("%.10f", sub));

    }

    public static void resBigView(double x, BigDecimal epsilon, BigDecimal myRes, double javaRes){

        BigDecimal sub = new BigDecimal(javaRes);
        sub.subtract(myRes);

        System.out.println("точность: " + epsilon + "\nx: " + x + "\nмоя функция: " +  String.format("%.3f", myRes) +
                "\nстандартная функция: " + String.format("%.3f", javaRes) + "\nпогрешноссть вычисления: "
                + sub);

    }
}
