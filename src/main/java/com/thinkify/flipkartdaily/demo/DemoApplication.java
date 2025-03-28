package com.thinkify.flipkartdaily.demo;

import com.thinkify.flipkartdaily.model.Item;
import com.thinkify.flipkartdaily.service.InventoryService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
@Profile("demo")
class DemoApplication implements CommandLineRunner {
    private final InventoryService inventoryService;

    public DemoApplication(InventoryService inventoryService) {
        this.inventoryService = inventoryService;
    }

    @Override
    public void run(String... args) {
        inventoryService.addItem(new Item(null, "Milk", 10, 100, "Dairy", "Amul", "Fresh Amul Milk", new Date(), new Date()));
        inventoryService.addItem(new Item(null, "Bread", 5, 50, "Bakery", "Britannia", "Healthy bread", new Date(), new Date()));
        inventoryService.addInventory("Milk", 20);
        inventoryService.addInventory("Bread", 10);
        System.out.println("Search sorted by price: " + inventoryService.searchItems(null, null, null, null, "price"));
    }
}

