package com.knapsack;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in); //do odczytu wejscia

        try {
            //pobranie danych z wejscia
            int seed = getPositiveIntInput(scanner, "Podaj seed (liczba całkowita dodatnia):");
            int capacity = getPositiveIntInput(scanner, "Podaj pojemność plecaka (liczba całkowita dodatnia):");
            int numberOfItems = getPositiveIntInput(scanner, "Podaj liczbę przedmiotów (liczba całkowita dodatnia):");

            //granice dla generatora
            int lowerBound = 1;
            int upperBound = 10;

            //tworzenie problemu
            Problem problem = new Problem(numberOfItems, seed, lowerBound, upperBound);
            System.out.println("\nWygenerowany problem:");
            System.out.println(problem.toString());

            //rozwiazanie
            Result solution = problem.solve(capacity);

            //wyswietlanie wyniku
            System.out.println("\nPojemność plecaka: " + capacity);
            System.out.println(solution.toString());

        } catch (Exception e) {
            System.out.println("Wystąpił błąd: " + e.getMessage());
        } finally {
            scanner.close(); //zamykamy skaner
        }
    }

    private static int getPositiveIntInput(Scanner scanner, String prompt) {
        while (true) {
            try {
                System.out.println(prompt);
                int value = scanner.nextInt();
                if (value <= 0) {
                    throw new IllegalArgumentException("Wartość musi być większa od 0");
                }
                return value;
            } catch (Exception e) {
                System.out.println("Nieprawidłowe dane. Spróbuj ponownie.");
                scanner.nextLine(); // Czyść bufor wejścia
            }
        }
    }
}
