package ua.kpi.comsys.dop;

import org.junit.jupiter.api.Test;
import java.math.BigDecimal;
import java.time.LocalDate;
import static org.junit.jupiter.api.Assertions.*;

class OrderProcessorTest {
    private final OrderProcessor processor = new OrderProcessor();
    @Test
    void testPending() {
        assertEquals(
                "Order Status: PENDING\n" +
                        "Details: Order is currently pending processing.",
                processor.process(new Pending())
        );
    }

    @Test
    void testPaid() {
        assertEquals(
                "Order Status: PAID\n" +
                        "Details: Payment ID: 0000, Amount: $152.75",
                processor.process(new Paid("0000", new BigDecimal("152.75")))
        );
    }

    @Test
    void testShipped() {
        assertEquals(
                "Order Status: SHIPPED\n" +
                        "Details: Tracking Code: 0001, Dispatch Date: 2026-10-10",
                processor.process(new Shipped("0001", LocalDate.of(2026, 10, 10)))
        );
    }

    @Test
    void testCancelled() {
        assertEquals(
                "Order Status: CANCELLED\n" +
                        "Details: Reason: Customer request",
                processor.process(new Cancelled("Customer request"))
        );
    }

    @Test
    void testNullStatus() {
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> processor.process(null)
        );

        assertEquals("Status cannot be null", exception.getMessage());
    }

    @Test
    void testNullPaymentId() {
        assertThrows(IllegalArgumentException.class,
                () -> new Paid(null, BigDecimal.TEN));
    }

    @Test
    void testBlankPaymentId() {
        assertThrows(IllegalArgumentException.class,
                () -> new Paid("   ", BigDecimal.TEN));
    }

    @Test
    void testNullAmount() {
        assertThrows(IllegalArgumentException.class,
                () -> new Paid("0000", null));
    }

    @Test
    void testZeroAmount() {
        assertThrows(IllegalArgumentException.class,
                () -> new Paid("0000", BigDecimal.ZERO));
    }

    @Test
    void testNegativeAmount() {
        assertThrows(IllegalArgumentException.class,
                () -> new Paid("0000", new BigDecimal("-10")));
    }

    @Test
    void testNullTrackingCode() {
        assertThrows(IllegalArgumentException.class,
                () -> new Shipped(null, LocalDate.now()));
    }

    @Test
    void testBlankTrackingCode() {
        assertThrows(IllegalArgumentException.class,
                () -> new Shipped("   ", LocalDate.now()));
    }

    @Test
    void testNullDispatchDate() {
        assertThrows(IllegalArgumentException.class,
                () -> new Shipped("0001", null));
    }

    @Test
    void testNullCancellationReason() {
        assertThrows(IllegalArgumentException.class,
                () -> new Cancelled(null));
    }

    @Test
    void testBlankCancellationReason() {
        assertThrows(IllegalArgumentException.class,
                () -> new Cancelled("   "));
    }
}
