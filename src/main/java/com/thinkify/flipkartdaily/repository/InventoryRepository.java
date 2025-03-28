package com.thinkify.flipkartdaily.repository;

import com.thinkify.flipkartdaily.model.Item;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class InventoryRepository {
    private final Map<String, Item> inventory = new HashMap<>();

    public void saveItem(Item item) {
        inventory.put(item.getName(), item);
    }

    public Item findByName(String name) {
        return inventory.get(name);
    }

    public List<Item> getAllItems() {
        return new ArrayList<>(inventory.values());
    }
}
