package org.lld.designpatterns.structural.adapter;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class PayPalPaymentAdapterTest {

    @Test
    public void testPayReturnsPayPalId() {
        PayPalPaymentAdapter adapter = new PayPalPaymentAdapter();
        String paymentId = adapter.pay(99.99, "USD");
        assertNotNull(paymentId);
        assertTrue(paymentId.startsWith("PAYPAL_"));
    }

    @Test
    public void testGetStatusReturnsCompleted() {
        PayPalPaymentAdapter adapter = new PayPalPaymentAdapter();
        PaymentStatus status = adapter.getStatus("PAYPAL_ANY_ID");
        assertEquals(PaymentStatus.COMPLETED, status);
    }

    @Test
    public void testPayWithLargeAmount() {
        PayPalPaymentAdapter adapter = new PayPalPaymentAdapter();
        String id = adapter.pay(Double.MAX_VALUE, "USD");
        assertNotNull(id);
        assertTrue(id.startsWith("PAYPAL_"));
    }

    @Test
    public void testGetStatusWithUnknownIdMapsToFailed() {
        PayPalPaymentAdapter adapter = new PayPalPaymentAdapter();
        PaymentStatus status = adapter.getStatus("unknown-id");
        assertEquals(PaymentStatus.FAILED, status);
    }
}

