package com.knapsack;


import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ProblemTest {

    /**
     * Test sprawdza, czy jeśli istnieje przynajmniej jeden przedmiot,
     * który mieści się do plecaka, to wynik zawiera co najmniej jeden taki przedmiot.
     */
    @Test
    public void testReturnsAtLeastOneItemIfFits() {
        // co najmniej jeden przedmiot pasuje do plecaka
        Problem problem = new Problem(5, 123, 1, 5);
        int capacity = 10;
        Result result = problem.solve(capacity);

        assertFalse(result.getItemNumbers().isEmpty(), "Powinien zostać wybrany co najmniej jeden przedmiot");
        assertTrue(result.getTotalWeight() <= capacity, "Łączna waga nie powinna przekraczać pojemności");
    }


    /**
     * Test sprawdza, czy jeśli żaden przedmiot nie spełnia ograniczenia wagowego,
     * to rozwiązanie jest puste (żaden przedmiot nie został wybrany).
     */
    @Test
    public void testReturnsEmptyIfNothingFits() {
        Problem problem = new Problem(5, 42, 10, 20); // Wagi >= 10
        int capacity = 5; // Mniejsza niż jakakolwiek waga
        Result result = problem.solve(capacity);

        assertTrue(result.getItemNumbers().isEmpty(), "Lista przedmiotów powinna być pusta");
        assertEquals(0, result.getTotalWeight(), "Całkowita waga powinna wynosić 0");
        assertEquals(0, result.getTotalValue(), "Całkowita wartość powinna wynosić 0");
    }
    /**
     * Test sprawdza, czy wartości i wagi wszystkich wygenerowanych przedmiotów
     * mieszczą się w zadanym przedziale (lowerBound, upperBound).
     */
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

    /**
     * Test sprawdza poprawność działania algorytmu dla konkretnej instancji danych.
     * Ręcznie tworzymy prosty przypadek i porównujemy oczekiwane wyniki z rzeczywistymi.
     */
    @Test
    public void testCorrectnessOfKnownSolution() {
        // Wszystkie wartości i wagi będą równe 1
        Problem problem = new Problem(3, 1, 1, 1);
        int capacity = 2;
        Result result = problem.solve(capacity);

        assertEquals(2, result.getTotalValue(), "Całkowita wartość powinna wynosić 2");
        assertEquals(2, result.getTotalWeight(), "Całkowita waga powinna wynosić 2");
        assertEquals(2, result.getItemNumbers().size(), "Powinny być wybrane 2 przedmioty");
    }
}
