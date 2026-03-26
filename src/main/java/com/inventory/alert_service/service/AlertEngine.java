package com.inventory.alert_service.service;

import com.inventory.alert_service.domain.AlertEvent;
import com.inventory.alert_service.domain.Product;
import com.inventory.alert_service.listener.AlertListener;
import org.springframework.stereotype.Service;
import java.time.Instant;
import java.util.List;

@Service
public class AlertEngine {
    private final List<AlertListener> listeners;

    public AlertEngine(List<AlertListener> listeners) {
        this.listeners = listeners;
    }

    public void evaluate(Product product) {
        if (product.getCurrentStock() >= product.getReorderThreshold()) return;
        double severityLimit = product.getReorderThreshold() * 0.10;
        AlertEvent.Severity severity = (product.getCurrentStock() <= severityLimit) 
                ? AlertEvent.Severity.CRITICAL 
                : AlertEvent.Severity.LOW;

        AlertEvent event = new AlertEvent(severity, product, Instant.now());
        listeners.forEach(l -> l.onAlert(event));
    }
}