package com.gmail.dario.reactors.utils;

import java.math.BigDecimal;
import java.util.Collection;

public class FastMath {
    public static double sum(Collection<Double> numbers){
        return numbers.stream().reduce(0.0, (a, b) -> a + b);
    }

    public static Double average(Collection<Double> numbers){
        return sum(numbers) / numbers.size();
    }
}
