package org.lld.designpatterns.behavioural.strategy.service;

import org.lld.designpatterns.behavioural.strategy.PaymentStrategy;

public class PaymentService {
    private PaymentStrategy paymentStrategy;

    public PaymentService(PaymentStrategy paymentStrategy) {
        this.paymentStrategy = paymentStrategy;
    }

    public void makePayment(double amount) {
        paymentStrategy.pay(amount);
    }

    public void changePaymentMethod(PaymentStrategy paymentStrategy) {
        this.paymentStrategy = paymentStrategy;
    }
}
