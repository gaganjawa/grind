package org.lld.designpatterns.creational.abstractfactory;

public class PayPalGateway implements PaymentGateway {
    @Override
    public void processPayment(double amount) {
        System.out.println("Processing payment of USD " + amount + " through PayPal.");
    }
}
