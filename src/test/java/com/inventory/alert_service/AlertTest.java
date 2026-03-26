package com.inventory.alert_service;

import com.inventory.alert_service.domain.AlertEvent;
import com.inventory.alert_service.domain.Product;
import com.inventory.alert_service.listener.AlertListener;
import com.inventory.alert_service.service.AlertEngine;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.ArgumentCaptor;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class AlertTest {
    private AlertEngine alertEngine;
    private AlertListener mockListener;

    @BeforeEach
    void setUp() {
        mockListener = mock(AlertListener.class);
        alertEngine = new AlertEngine(List.of(mockListener));
    }

    @Test
    @DisplayName("Should notify all registered listeners when an alert is triggered")
    void testAllListenersReceiveEvent() {
        AlertListener mock1 = mock(AlertListener.class);
        AlertListener mock2 = mock(AlertListener.class);

        AlertEngine engine = new AlertEngine(List.of(mock1, mock2));

        Product p = Product.builder().sku("P1").reorderThreshold(10).currentStock(5).build();
        engine.evaluate(p);

        verify(mock1, times(1)).onAlert(any(AlertEvent.class));
        verify(mock2, times(1)).onAlert(any(AlertEvent.class));
    }

}
