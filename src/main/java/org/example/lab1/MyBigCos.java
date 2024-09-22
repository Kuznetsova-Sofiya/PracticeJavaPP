package org.example.lab1;

import java.math.BigDecimal;
import java.math.MathContext;

public class MyBigCos {
    private BigDecimal epsilon;

    public void setEpsilon(int k) {
        epsilon = BigDecimal.ONE.divide(BigDecimal.TEN.pow(k), MathContext.DECIMAL128);
    }

    public BigDecimal getEpsilon() {
        return epsilon;
    }

    public BigDecimal iteration(BigDecimal last, BigDecimal x, int n) {
        BigDecimal numerator = last.multiply(new BigDecimal(-1)).multiply(x).multiply(x);
        BigDecimal denominator = new BigDecimal((2 * n + 1) * (2 * n + 2));
        return numerator.divide(denominator, MathContext.DECIMAL128);
    }

    public BigDecimal calculation(BigDecimal x) {
        BigDecimal res = BigDecimal.ZERO;
        BigDecimal tempRes = BigDecimal.ONE;
        int n = 0;

        while (tempRes.abs().compareTo(epsilon) > 0) {
            res = res.add(tempRes);
            tempRes = iteration(tempRes, x, n);
            n++;
        }
        res = res.add(tempRes);

        return res;
    }
}
