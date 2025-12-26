package org.lld.designpatterns.structural.adapter;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class RazorpayPaymentGatewayTest {

    @Test
    public void testPayReturnsSimulatedId() {
        RazorpayPaymentGateway gateway = new RazorpayPaymentGateway();
        String paymentId = gateway.pay(50.0, "INR");
        assertNotNull(paymentId);
        assertEquals("RAZORPAY12345", paymentId);
    }

    @Test
    public void testGetStatusReturnsCompleted() {
        RazorpayPaymentGateway gateway = new RazorpayPaymentGateway();
        PaymentStatus status = gateway.getStatus("RAZORPAY12345");
        assertEquals(PaymentStatus.COMPLETED, status);
    }

    @Test
    public void testPayWithEmptyCurrency() {
        RazorpayPaymentGateway gateway = new RazorpayPaymentGateway();
        String id = gateway.pay(20.0, "");
        assertNotNull(id);
        assertEquals("RAZORPAY12345", id);
    }

    @Test
    public void testGetStatusWithEmptyIdThrows() {
        RazorpayPaymentGateway gateway = new RazorpayPaymentGateway();
        assertThrows(IllegalArgumentException.class, () -> gateway.getStatus(""));
    }
}

