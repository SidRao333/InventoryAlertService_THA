package com.inventory.alert_service;

import com.inventory.alert_service.domain.Product;
import com.inventory.alert_service.exception.InsufficientStockException;
import com.inventory.alert_service.service.InventoryService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class InventoryServiceTest {
    private InventoryService inventoryService;

    @BeforeEach
    void setUp() {
        inventoryService = new InventoryService();
    }

    @Test
    @DisplayName("Should increment and decrement stock correctly")
    void testUpdateStock_Success() {
        Product p = Product.builder().sku("SKU1").currentStock(10).reorderThreshold(5).build();
        inventoryService.addProduct(p);

        inventoryService.updateStock("SKU1", 5);
        assertEquals(15, p.getCurrentStock());

        inventoryService.updateStock("SKU1", -7);
        assertEquals(8, p.getCurrentStock());

    }

    @Test
    @DisplayName("Should throw exception when stock goes below zero")
    void testUpdateStock_ThrowsException() {
        Product p = Product.builder().sku("SKU1").currentStock(5).reorderThreshold(10).build();
        inventoryService.addProduct(p);

        assertThrows(InsufficientStockException.class, () -> inventoryService.updateStock("SKU1", -6));
    }
}
