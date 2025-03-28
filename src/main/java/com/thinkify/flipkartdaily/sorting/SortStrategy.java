package com.thinkify.flipkartdaily.sorting;

import com.thinkify.flipkartdaily.model.Item;

import java.util.List;

public interface SortStrategy {
    void sort(List<Item> items);

}
