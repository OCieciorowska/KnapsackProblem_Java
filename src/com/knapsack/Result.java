package com.knapsack;

import java.util.ArrayList;
import java.util.List;

public class Result {
    private List<Integer> itemNumbers;
    private List<Integer> quantities;
    private int totalValue;
    private int totalWeight;

    public Result(List<Integer> itemNumbers, List<Integer> quantities, int totalValue, int totalWeight) {
        this.itemNumbers = new ArrayList<>(itemNumbers);
        this.quantities = new ArrayList<>(quantities);
        this.totalValue = totalValue;
        this.totalWeight = totalWeight;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Solution:\n");
        sb.append("Items in knapsack:\n");

        for (int i = 0; i < itemNumbers.size(); i++) {
            sb.append("Item ").append(itemNumbers.get(i) + 1)  // 👈 dodano +1
                    .append(": Quantity=").append(quantities.get(i))
                    .append("\n");
        }

        sb.append("Total value: ").append(totalValue).append("\n");
        sb.append("Total weight: ").append(totalWeight).append("\n");

        return sb.toString();
    }

    public List<Integer> getItemNumbers() { return itemNumbers; }
    public List<Integer> getQuantities() { return quantities; }
    public int getTotalValue() { return totalValue; }
    public int getTotalWeight() { return totalWeight; }
}
