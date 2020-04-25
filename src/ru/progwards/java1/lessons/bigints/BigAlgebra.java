package ru.progwards.java1.lessons.bigints;

import java.math.BigDecimal;
import java.math.BigInteger;

public class BigAlgebra {

    public static BigDecimal fastPow(BigDecimal num, int pow) {
        BigDecimal res = new BigDecimal("1");
        while (pow != 0) {
            if ((pow & 1) != 0)
                res = res.multiply(num);
            num = num.multiply(num);
            pow >>= 1;
        }
        return res;
    }

    public static BigInteger fibonacci(int n){
        BigInteger prev = new BigInteger("0");
        BigInteger next = new BigInteger("1");
        for(int i = 0; i < n; i++){
            next = prev.add(next);
            prev = next.subtract(prev);
        }
        return prev;
    }

}
