package com.knapsack;

import java.util.*;

public class Problem {
    private int numberOfItems;
    private int seed;
    private int lowerBound;
    private int upperBound;
    private List<Item> items;

    public Problem(int numberOfItems, int seed, int lowerBound, int upperBound) {
        this.numberOfItems = numberOfItems;
        this.seed = seed;
        this.lowerBound = lowerBound;
        this.upperBound = upperBound;
        this.items = generateItems();
    }

    public Result solve(int capacity) {
        List<ItemWithIndex> sortedItems = new ArrayList<>();
        for (int i = 0; i < items.size(); i++) {
            Item item = items.get(i);
            double ratio = (double) item.getValue() / item.getWeight();
            sortedItems.add(new ItemWithIndex(i, item, ratio));
        }

        sortedItems.sort((a, b) -> Double.compare(b.ratio, a.ratio));

        List<Integer> selectedItemNumbers = new ArrayList<>();
        List<Integer> selectedQuantities = new ArrayList<>();
        int remainingCapacity = capacity;
        int totalValue = 0;
        int totalWeight = 0;

        for (ItemWithIndex itemWithIndex : sortedItems) {
            if (remainingCapacity <= 0) break;

            Item item = itemWithIndex.item;
            if (item.getWeight() <= remainingCapacity) {
                int quantity = remainingCapacity / item.getWeight();
                selectedItemNumbers.add(itemWithIndex.index);
                selectedQuantities.add(quantity);
                totalValue += quantity * item.getValue();
                totalWeight += quantity * item.getWeight();
                remainingCapacity -= quantity * item.getWeight();
            }
        }

        return new Result(selectedItemNumbers, selectedQuantities, totalValue, totalWeight);
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
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Problem instance:\n");
        sb.append("Number of items: ").append(numberOfItems).append("\n");
        sb.append("Seed: ").append(seed).append("\n");
        sb.append("Bounds: [").append(lowerBound).append(", ").append(upperBound).append("]\n");
        sb.append("Items:\n");

        for (int i = 0; i < items.size(); i++) {
            sb.append("Item ").append(i + 1).append(": value=")
                    .append(items.get(i).getValue()).append(", weight=")
                    .append(items.get(i).getWeight()).append("\n");
        }

        return sb.toString();
    }

    private static class ItemWithIndex {
        int index;
        Item item;
        double ratio;

        ItemWithIndex(int index, Item item, double ratio) {
            this.index = index;
            this.item = item;
            this.ratio = ratio;
        }
    }

    public List<Item> getItems() {
        return items;
    }
}
