package com.thinkify.flipkartdaily.sorting;

import com.thinkify.flipkartdaily.model.Item;

import java.util.Comparator;
import java.util.List;

class QuantitySortStrategy implements SortStrategy {
    @Override
    public void sort(List<Item> items) {
        items.sort(Comparator.comparingInt(Item::getQuantity));
    }
}
