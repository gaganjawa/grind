package org.lld.designpatterns.structural.adapter;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class StripePaymentAdapterTest {

    @Test
    public void testPayReturnsStripeId() {
        StripePaymentAdapter adapter = new StripePaymentAdapter();
        String paymentId = adapter.pay(12.34, "USD");
        assertNotNull(paymentId, "Stripe payment id should not be null");
        assertTrue(paymentId.startsWith("STRIPE_"), "Stripe payment id should start with STRIPE_");
    }

    @Test
    public void testGetStatusReturnsCompleted() {
        StripePaymentAdapter adapter = new StripePaymentAdapter();
        PaymentStatus status = adapter.getStatus("any-id");
        assertEquals(PaymentStatus.COMPLETED, status, "Stripe adapter should report COMPLETED when client returns true");
    }

    @Test
    public void testPayWithZeroAmountThrows() {
        StripePaymentAdapter adapter = new StripePaymentAdapter();
        assertThrows(IllegalArgumentException.class, () -> adapter.pay(0.0, "USD"));
    }

    @Test
    public void testPayWithNegativeAmountThrows() {
        StripePaymentAdapter adapter = new StripePaymentAdapter();
        assertThrows(IllegalArgumentException.class, () -> adapter.pay(-10.0, "USD"));
    }

    @Test
    public void testGetStatusWithNullIdThrows() {
        StripePaymentAdapter adapter = new StripePaymentAdapter();
        assertThrows(IllegalArgumentException.class, () -> adapter.getStatus(null));
    }
}

