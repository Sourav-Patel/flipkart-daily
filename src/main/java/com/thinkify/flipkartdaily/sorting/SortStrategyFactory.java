package com.thinkify.flipkartdaily.sorting;

public class SortStrategyFactory {
    public static SortStrategy getSortStrategy(String sortBy) {
        if ("price".equalsIgnoreCase(sortBy)) {
            return new PriceSortStrategy();
        } else if ("quantity".equalsIgnoreCase(sortBy)) {
            return new QuantitySortStrategy();
        } else {
            return new NameSortStrategy(); // Default sorting by name
        }
    }
}