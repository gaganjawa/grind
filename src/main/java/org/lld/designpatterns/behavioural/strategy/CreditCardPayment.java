package org.lld.designpatterns.behavioural.strategy;

public class CreditCardPayment implements PaymentStrategy {

    private final String creditCardNumber;

    public CreditCardPayment(String creditCardNumber) {
        this.creditCardNumber = creditCardNumber;
    }

    @Override
    public void pay(double amount) {
        System.out.println("Paid " + amount + " using credit card: "+ creditCardNumber);
    }
}
