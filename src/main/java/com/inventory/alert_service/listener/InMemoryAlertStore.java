package com.inventory.alert_service.listener;

import com.inventory.alert_service.domain.AlertEvent;
import lombok.Getter;
import org.springframework.stereotype.Component;
import java.util.ArrayList;
import java.util.List;

@Component
public class InMemoryAlertStore implements AlertListener {
    @Getter
    private final List<AlertEvent> alerts = new ArrayList<>();

    @Override
    public void onAlert(AlertEvent event) {
        alerts.add(event);
    }
}