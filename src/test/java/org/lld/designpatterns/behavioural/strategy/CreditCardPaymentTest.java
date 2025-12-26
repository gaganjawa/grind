package org.lld.designpatterns.behavioural.strategy;

import org.junit.jupiter.api.Test;
import org.lld.designpatterns.TestUtils;

import static org.junit.jupiter.api.Assertions.*;

class CreditCardPaymentTest {

    @Test
    void testPay_printsExpectedMessage() {
        TestUtils tu = new TestUtils();
        CreditCardPayment p = new CreditCardPayment("4111-1111");

        tu.startCapture();
        p.pay(10.0);
        String out = tu.stopAndGetOutput();

        assertTrue(out.contains("Paid 10.0 using credit card: 4111-1111"));
    }
}

