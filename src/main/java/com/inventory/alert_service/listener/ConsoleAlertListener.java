package com.inventory.alert_service.listener;

import com.inventory.alert_service.domain.AlertEvent;
import org.springframework.stereotype.Component;

@Component
public class ConsoleAlertListener implements AlertListener {
    @Override
    public void onAlert(AlertEvent event) {
        System.out.printf("[%s] ALERT: %s - Product: %s (Stock: %d)%n",
                event.severity(), event.timestamp(), event.product().getName(), event.product().getCurrentStock());
    }
}