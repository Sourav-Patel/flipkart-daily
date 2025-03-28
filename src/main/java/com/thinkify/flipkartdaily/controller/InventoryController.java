package com.thinkify.flipkartdaily.controller;

import com.thinkify.flipkartdaily.model.Item;
import com.thinkify.flipkartdaily.service.InventoryService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/inventory")
@AllArgsConstructor
public class InventoryController {
    private final InventoryService inventoryService;

    @PostMapping("/addItem")
    public ResponseEntity<String> addItem(@RequestBody Item item) {
        try {
            inventoryService.addItem(item);
            return ResponseEntity.status(HttpStatus.CREATED).body("Item added successfully");
        } catch (ResponseStatusException e) {
            return ResponseEntity.status(e.getStatusCode()).body(e.getReason());
        }
    }

    @PostMapping("/addInventory")
    public ResponseEntity<String> addInventory(@RequestParam String name, @RequestParam int quantity) {
        try {
            inventoryService.addInventory(name, quantity);
            return ResponseEntity.ok("Inventory updated successfully");
        } catch (ResponseStatusException e) {
            return ResponseEntity.status(e.getStatusCode()).body(e.getReason());
        }
    }

    @GetMapping("/search")
    public ResponseEntity<List<Item>> searchItems(
            @RequestParam(required = false) String category,
            @RequestParam(required = false) String brand,
            @RequestParam(required = false) Double priceFrom,
            @RequestParam(required = false) Double priceTo,
            @RequestParam(required = false, defaultValue = "price") String sortBy) {
        try {
            return ResponseEntity.ok(inventoryService.searchItems(category, brand, priceFrom, priceTo, sortBy));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Collections.emptyList());
        }
    }
}
