package com.thinkify.flipkartdaily.service;


import com.thinkify.flipkartdaily.model.Item;
import com.thinkify.flipkartdaily.repository.InventoryRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class InventoryServiceTest {

    @Mock
    private InventoryRepository inventoryRepository;

    @InjectMocks
    private InventoryService inventoryService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testAddItem_Success() {
        Item item = new Item(null, "Laptop", 5, 1000, "Electronics", "Dell", "Gaming Laptop", new Date(), new Date());

        inventoryService.addItem(item);

        verify(inventoryRepository, times(1)).saveItem(any(Item.class));
        assertNotNull(item.getId());
    }

    @Test
    void testAddItem_ThrowsException_WhenNameIsEmpty() {
        Item item = new Item(null, "", 5, 1000, "Electronics", "Dell", "Gaming Laptop", new Date(), new Date());

        ResponseStatusException exception = assertThrows(ResponseStatusException.class, () -> {
            inventoryService.addItem(item);
        });

        assertEquals("400 BAD_REQUEST \"Item name cannot be empty\"", exception.getMessage());
    }

    @Test
    void testAddInventory_Success() {
        Item item = new Item("1", "Milk", 10, 50, "Dairy", "Amul", "Fresh Amul Milk", new Date(), new Date());
        when(inventoryRepository.findByName("Milk")).thenReturn(item);

        inventoryService.addInventory("Milk", 5);

        assertEquals(15, item.getQuantity());
        verify(inventoryRepository, times(1)).saveItem(item);
    }

    @Test
    void testAddInventory_ThrowsException_WhenItemNotFound() {
        when(inventoryRepository.findByName("Milk")).thenReturn(null);

        ResponseStatusException exception = assertThrows(ResponseStatusException.class, () -> {
            inventoryService.addInventory("Milk", 5);
        });

        assertEquals("404 NOT_FOUND \"Item does not exist\"", exception.getMessage());
    }

    @Test
    void testSearchItems() {
        List<Item> items = new ArrayList<>();
        items.add(new Item("1", "Milk", 10, 50, "Dairy", "Amul", "Fresh Amul Milk", new Date(), new Date()));
        when(inventoryRepository.getAllItems()).thenReturn(items);

        List<Item> result = inventoryService.searchItems("Dairy", null, null, null, "price");

        assertEquals(1, result.size());
    }
}
