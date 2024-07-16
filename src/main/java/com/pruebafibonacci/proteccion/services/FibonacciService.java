package com.pruebafibonacci.proteccion.services;

import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
public class FibonacciService {

    public List<Integer> generateFibonacciSeries(int seedX, int seedY, int count) {
        List<Integer> fibonacciSeries = new ArrayList<>();
        fibonacciSeries.add(seedX);
        fibonacciSeries.add(seedY);

        for (int i = 2; i < count + 2; i++) {
            int nextNumber = fibonacciSeries.get(i - 1) + fibonacciSeries.get(i - 2);
            fibonacciSeries.add(nextNumber);
        }

        // List<Integer> result = fibonacciSeries.subList(2, fibonacciSeries.size());
        // result.sort((a, b) -> b - a); 
        // return result;

        fibonacciSeries.sort((a,b) -> b-a);
        return fibonacciSeries;
    }
}
