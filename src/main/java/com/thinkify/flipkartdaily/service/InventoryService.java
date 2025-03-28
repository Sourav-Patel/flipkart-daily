package com.thinkify.flipkartdaily.service;

import com.thinkify.flipkartdaily.model.Item;
import com.thinkify.flipkartdaily.repository.InventoryRepository;
import com.thinkify.flipkartdaily.sorting.SortStrategy;
import com.thinkify.flipkartdaily.sorting.SortStrategyFactory;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class InventoryService {
    private final InventoryRepository inventoryRepository;

    public void addItem(Item item) {
        if (item.getName() == null || item.getName().isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Item name cannot be empty");
        }
        item.setId(UUID.randomUUID().toString());
        item.setCreatedAt(new Date());
        item.setUpdatedAt(new Date());
        inventoryRepository.saveItem(item);
    }

    public void addInventory(String name, int quantity) {
        Item item = inventoryRepository.findByName(name);
        if (item == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Item does not exist");
        }
        item.setQuantity(item.getQuantity() + quantity);
        item.setUpdatedAt(new Date());
        inventoryRepository.saveItem(item);
    }

    public List<Item> searchItems(String category, String brand, Double priceFrom, Double priceTo, String sortBy) {
        List<Item> itemList = inventoryRepository.getAllItems();

        // Filtering
        if (category != null) {
            itemList = itemList.stream().filter(item -> item.getCategory().equalsIgnoreCase(category)).collect(Collectors.toList());
        }
        if (brand != null) {
            itemList = itemList.stream().filter(item -> item.getBrand().equalsIgnoreCase(brand)).collect(Collectors.toList());
        }
        if (priceFrom != null) {
            itemList = itemList.stream().filter(item -> item.getPrice() >= priceFrom).collect(Collectors.toList());
        }
        if (priceTo != null) {
            itemList = itemList.stream().filter(item -> item.getPrice() <= priceTo).collect(Collectors.toList());
        }

        // Sorting
        SortStrategy sortStrategy = SortStrategyFactory.getSortStrategy(sortBy);
        sortStrategy.sort(itemList);
        return itemList;
    }
}