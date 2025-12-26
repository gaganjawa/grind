package org.lld.designpatterns.creational.abstractfactory;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class USAFactoryTest {

    @Test
    void testCreatePaymentGateway_paypal() {
        USAFactory f = new USAFactory();
        PaymentGateway g = f.createPaymentGateway("PayPal");
        assertTrue(g instanceof PayPalGateway);
    }

    @Test
    void testCreatePaymentGateway_unknown_throws() {
        USAFactory f = new USAFactory();
        assertThrows(IllegalArgumentException.class, () -> f.createPaymentGateway("UnknownGateway"));
    }

    @Test
    void testCreateInvoice_returnsUSInvoice() {
        USAFactory f = new USAFactory();
        Invoice inv = f.createInvoice();
        assertTrue(inv instanceof USInvoice);
    }
}

