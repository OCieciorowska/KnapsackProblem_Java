package com.knapsack;


import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ProblemTest {

    @Test
    public void testReturnsAtLeastOneItemIfFits() {
        // co najmniej jeden przedmiot pasuje do plecaka
        Problem problem = new Problem(5, 123, 1, 5);
        int capacity = 10;
        Result result = problem.solve(capacity);

        int totalQuantity = result.getQuantities().stream().mapToInt(Integer::intValue).sum();
        assertTrue(totalQuantity > 0, "Powinien zostać wybrany co najmniej jeden przedmiot");
    }

    @Test
    public void testReturnsEmptyIfNothingFits() {
        // żaden przedmiot nie mieści się do plecaka
        Problem problem = new Problem(5, 42, 10, 20); // wagi co najmniej 10
        int capacity = 5; // mniejsza niż jakakolwiek waga
        Result result = problem.solve(capacity);

        assertTrue(result.getItemNumbers().isEmpty(), "Lista przedmiotów powinna być pusta");
        assertTrue(result.getQuantities().isEmpty(), "Lista ilości powinna być pusta");
        assertEquals(0, result.getTotalWeight(), "Całkowita waga powinna wynosić 0");
        assertEquals(0, result.getTotalValue(), "Całkowita wartość powinna wynosić 0");
    }

    @Test
    public void testItemGenerationWithinBounds() {
        // sprawdzenie zakresu wartości i wag
        int n = 100;
        int seed = 456;
        int lowerBound = 3;
        int upperBound = 8;

        Problem problem = new Problem(n, seed, lowerBound, upperBound);
        List<Item> items = problem.getItems();

        assertEquals(n, items.size(), "Liczba wygenerowanych przedmiotów powinna wynosić " + n);

        for (Item item : items) {
            assertTrue(item.getWeight() >= lowerBound && item.getWeight() <= upperBound,
                    "Waga poza zakresem: " + item.getWeight());
            assertTrue(item.getValue() >= lowerBound && item.getValue() <= upperBound,
                    "Wartość poza zakresem: " + item.getValue());
        }
    }

    @Test
    public void testCorrectnessOfKnownSolution() {
        // test deterministyczny z konkretnym seedem
        Problem problem = new Problem(3, 1, 1, 1); // wszystkie przedmioty będą miały wagę=1, wartość=1
        int capacity = 2;

        Result result = problem.solve(capacity);

        assertEquals(2, result.getTotalValue(), "Całkowita wartość powinna wynosić 2");
        assertEquals(2, result.getTotalWeight(), "Całkowita waga powinna wynosić 2");
    }
}
