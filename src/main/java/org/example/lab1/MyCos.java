package org.example.lab1;

public class MyCos {
    private double epsilon;

    public void setEpsilon(int k) {
        epsilon = 1 / Math.pow(10, k);
    }

    public double getEpsilon() {
        return epsilon;
    }


    public double iteration(double last, double x, int n) {
        return last * (-1) * x * x / ((2 * n + 1) * (2 * n + 2));
    }

    public double calculation(double x) {
        double res = 0;
        double tempRes = 1;
        int n = 0;

        while (Math.abs(tempRes) > epsilon) {
            res += tempRes;
            tempRes = iteration(tempRes, x, n);
            n++;
        }
        res += tempRes;

        return res;
    }
}
