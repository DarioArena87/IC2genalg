package com.gmail.dario.reactors.utils;

import java.util.Collection;

public class FastMath {
    public static double sum(Collection<Double> numbers){
        return numbers.stream().reduce(0.0, Double::sum);
    }

    public static Double average(Collection<Double> numbers){
        return sum(numbers) / numbers.size();
    }
}
