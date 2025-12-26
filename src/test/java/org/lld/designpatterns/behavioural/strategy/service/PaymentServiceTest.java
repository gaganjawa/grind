package org.lld.designpatterns.behavioural.strategy.service;

import org.junit.jupiter.api.Test;
import org.lld.designpatterns.behavioural.strategy.PaymentStrategy;

import static org.junit.jupiter.api.Assertions.*;

class PaymentServiceTest {

    static class TestPaymentStrategy implements PaymentStrategy {
        double lastAmount = -1;

        @Override
        public void pay(double amount) {
            this.lastAmount = amount;
        }
    }

    @Test
    void testMakePayment_delegatesToStrategy() {
        TestPaymentStrategy s = new TestPaymentStrategy();
        PaymentService service = new PaymentService(s);

        service.makePayment(42.5);

        assertEquals(42.5, s.lastAmount);
    }

    @Test
    void testChangePaymentMethod_swapsStrategy() {
        TestPaymentStrategy s1 = new TestPaymentStrategy();
        TestPaymentStrategy s2 = new TestPaymentStrategy();
        PaymentService service = new PaymentService(s1);

        service.changePaymentMethod(s2);
        service.makePayment(10.0);

        assertEquals(-1, s1.lastAmount);
        assertEquals(10.0, s2.lastAmount);
    }
}

