package com.inventory.alert_service.listener;

import com.inventory.alert_service.domain.AlertEvent;

public interface AlertListener {
    void onAlert(AlertEvent event);
}