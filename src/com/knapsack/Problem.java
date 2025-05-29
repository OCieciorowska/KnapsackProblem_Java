package com.knapsack;

import java.util.*;

public class Problem {
    private int numberOfItems;
    private int seed;
    private int lowerBound;//granice wartosci i wagi przedmiotow
    private int upperBound;
    private List<Item> items;//lista przedmiotow

    public Problem(int numberOfItems, int seed, int lowerBound, int upperBound) {
        this.numberOfItems = numberOfItems;
        this.seed = seed;
        this.lowerBound = lowerBound;
        this.upperBound = upperBound;
        this.items = generateItems();//generator losowy
    }

    public Result solve(int capacity) {
        // Tworzymy listę pomocniczą z przedmiotami i ich oryginalnymi indeksami
        List<ItemWithIndex> indexedItems = new ArrayList<>();
        for (int i = 0; i < items.size(); i++) {
            Item item = items.get(i);
            double ratio = (double) item.getValue() / item.getWeight();
            indexedItems.add(new ItemWithIndex(i, item, ratio));
        }

        // Sortujemy według stosunku wartości do wagi malejąco
        indexedItems.sort((a, b) -> Double.compare(b.ratio, a.ratio));

        List<Integer> selectedItemNumbers = new ArrayList<>();
        int totalValue = 0;
        int totalWeight = 0;
        //przechodzimy przez posortowana liczbe i jesli dodanie przedmiotu nie przekroczy pojemnosci to dodajemy
        for (ItemWithIndex iw : indexedItems) {
            if (totalWeight + iw.item.getWeight() <= capacity) {
                selectedItemNumbers.add(iw.index); // dodajemy oryginalny indeks!
                totalValue += iw.item.getValue();
                totalWeight += iw.item.getWeight();
            }
        }

        return new Result(selectedItemNumbers, totalValue, totalWeight);
    }


    private List<Item> generateItems() {
        List<Item> items = new ArrayList<>();
        Random random = new Random(seed);

        for (int i = 0; i < numberOfItems; i++) {
            int value = random.nextInt(upperBound - lowerBound + 1) + lowerBound;
            int weight = random.nextInt(upperBound - lowerBound + 1) + lowerBound;
            items.add(new Item(value, weight));
        }

        return items;
    }

    @Override
    //generujemy string stanu problemu
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Problem instance:\n"); //sb.append daje dane na koniec budowanego tekstu
        sb.append("Number of items: ").append(numberOfItems).append("\n");
        sb.append("Seed: ").append(seed).append("\n");
        sb.append("Bounds: [").append(lowerBound).append(", ").append(upperBound).append("]\n");
        sb.append("Items:\n");

        for (int i = 0; i < items.size(); i++) {
            sb.append("Item ").append(i + 1).append(": value=")
                    .append(items.get(i).getValue()).append(", weight=")
                    .append(items.get(i).getWeight()).append("\n");
        }

        return sb.toString();//aby uzyskac gotowy ciag
    }
    //pomocnicza struktura przechowujaca indeksy i stosunek wagi do wartosci
    private static class ItemWithIndex {
        int index; //indeks przedmiotu
        Item item; //przedmiot
        double ratio; //stosunek wartosci do wagi

        ItemWithIndex(int index, Item item, double ratio) {
            this.index = index;
            this.item = item;
            this.ratio = ratio;
        }
    }
//getter listy przedmiotow
    public List<Item> getItems() {
        return items;
    }
}
