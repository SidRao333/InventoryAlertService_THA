package com.inventory.alert_service.controller;

import com.inventory.alert_service.domain.AlertEvent;
import com.inventory.alert_service.domain.Product;
import com.inventory.alert_service.listener.InMemoryAlertStore;
import com.inventory.alert_service.service.InventoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
public class ProductController {

    private final InventoryService inventoryService;
    private final InMemoryAlertStore alertStore;

    @PostMapping("/products")
    @ResponseStatus(HttpStatus.CREATED)
    public void addProduct(@RequestBody Product product) {
        inventoryService.addProduct(product);
    }

    @PatchMapping("/products/{sku}/stock")
    public void updateStock(
            @PathVariable String sku, 
            @RequestBody Map<String, Integer> requestBody) {
        
        Integer delta = requestBody.get("delta");
        if (delta == null) {
            throw new IllegalArgumentException("Must contain 'delta'");
        }
        inventoryService.updateStock(sku, delta);
    }

    @GetMapping("/alerts")
    public List<AlertEvent> getAlerts() {
        return alertStore.getAlerts();
    }
}