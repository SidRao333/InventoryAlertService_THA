package com.inventory.alert_service.domain;

import java.time.Instant;

public record AlertEvent(
    Severity severity,
    Product product,
    Instant timestamp
) {
    public enum Severity { LOW, CRITICAL }
}