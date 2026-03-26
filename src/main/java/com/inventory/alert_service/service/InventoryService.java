package com.inventory.alert_service.service;

import com.inventory.alert_service.domain.Product;
import com.inventory.alert_service.exception.InsufficientStockException;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.stereotype.Service;

@Service
public class InventoryService {

    private final Map<String, Product> products = new ConcurrentHashMap<>();
    private final AlertEngine alertEngine;

    public InventoryService(AlertEngine alertEngine) {
        this.alertEngine = alertEngine;
    }

    public void addProduct(Product product) {
        if (products.containsKey(product.getSku())) {
            throw new IllegalArgumentException("Duplicate SKU");
        }
        products.put(product.getSku(), product);
    }

    public void updateStock(String sku, int delta) {
        Product product = products.get(sku);
        if (product == null)
            throw new IllegalArgumentException("Product not found");

        int newStock = product.getCurrentStock() + delta;
        if (newStock < 0) {
            throw new InsufficientStockException("Stock cannot be negative for SKU: " + sku);
        }

        product.setCurrentStock(newStock);
        alertEngine.evaluate(product);
    }
}
