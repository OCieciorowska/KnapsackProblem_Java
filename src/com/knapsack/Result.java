package com.knapsack;

import java.util.ArrayList;
import java.util.List;

public class Result {
    private List<Integer> itemNumbers;
    private int totalValue;
    private int totalWeight;

    public Result(List<Integer> itemNumbers, int totalValue, int totalWeight) {
        this.itemNumbers = new ArrayList<>(itemNumbers);
        this.totalValue = totalValue;
        this.totalWeight = totalWeight;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Solution:\n");
        sb.append("Items in knapsack:\n");

        for (int itemNumber : itemNumbers) {
            sb.append("Item ").append(itemNumber+1).append("\n");
        }

        sb.append("Total value: ").append(totalValue).append("\n");
        sb.append("Total weight: ").append(totalWeight).append("\n");

        return sb.toString();
    }

    public List<Integer> getItemNumbers() { return itemNumbers; }
    public int getTotalValue() { return totalValue; }
    public int getTotalWeight() { return totalWeight; }
}