package com.gmail.dario.reactors.utils;

import java.math.BigDecimal;
import java.util.Collection;

public class FastMath {
    public static BigDecimal sum(Collection<BigDecimal> bigDecimals){
        return bigDecimals.stream().reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public static BigDecimal average(Collection<BigDecimal> bigDecimals){
        return sum(bigDecimals).divide(BigDecimal.valueOf(bigDecimals.size()), BigDecimal.ROUND_HALF_UP);
    }
}
