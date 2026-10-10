package ua.kpi.comsys.dop;

import java.time.LocalDate;

public record Shipped(String trackingCode, LocalDate dispatchDate)
        implements OrderStatus {

    public Shipped {
        if (trackingCode == null || trackingCode.isBlank()) {
            throw new IllegalArgumentException(
                    "Tracking code must exist"
            );
        }

        if (dispatchDate == null) {
            throw new IllegalArgumentException(
                    "Dispatch date must exist"
            );
        }
    }
}
