package org.lld.designpatterns.structural.adapter;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class PaymentServiceTest {

    @Test
    public void testPaymentServiceWithStripe() {
        StripePaymentAdapter stripe = new StripePaymentAdapter();
        PaymentService service = new PaymentService(stripe);

        String id = service.makePayment(20.0, "USD");
        assertNotNull(id);
        assertTrue(id.startsWith("STRIPE_"));

        PaymentStatus status = service.checkPaymentStatus(id);
        assertEquals(PaymentStatus.COMPLETED, status);
    }

    @Test
    public void testPaymentServiceWithRazorpay() {
        RazorpayPaymentGateway razor = new RazorpayPaymentGateway();
        PaymentService service = new PaymentService(razor);

        String id = service.makePayment(10.0, "INR");
        assertEquals("RAZORPAY12345", id);

        PaymentStatus status = service.checkPaymentStatus(id);
        assertEquals(PaymentStatus.COMPLETED, status);
    }

    @Test
    public void testMakePaymentWithNullCurrency() {
        PaymentGateway gateway = new RazorpayPaymentGateway();
        PaymentService service = new PaymentService(gateway);
        String id = service.makePayment(10.0, null);
        assertNotNull(id);
        assertEquals("RAZORPAY12345", id);
    }

    @Test
    public void testCheckPaymentStatusWithNullIdThrows() {
        PaymentGateway gateway = new StripePaymentAdapter();
        PaymentService service = new PaymentService(gateway);
        assertThrows(IllegalArgumentException.class, () -> service.checkPaymentStatus(null));
    }
}

