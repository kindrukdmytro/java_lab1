package ua.kpi.comsys.dop;

import java.math.BigDecimal;

public record Paid(String paymentID, BigDecimal amount)
        implements OrderStatus {

    public Paid {
        if (paymentID == null || paymentID.isBlank()) {
            throw new IllegalArgumentException(
                    "Payment ID must exist"
            );
        }

        if (amount == null || amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException(
                    "Amount must be positive"
            );
        }
    }
}
