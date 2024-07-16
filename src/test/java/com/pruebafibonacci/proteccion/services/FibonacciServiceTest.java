package com.pruebafibonacci.proteccion.services;

import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

public class FibonacciServiceTest {

    private final FibonacciService fibonacciService = new FibonacciService();

    @Test
    public void testGenerateFibonacciSeries() {
        List<Integer> result = fibonacciService.generateFibonacciSeries(2, 3, 4);
        List<Integer> expected = List.of(21, 13, 8, 5);
        assertEquals(expected, result);
    }

}
