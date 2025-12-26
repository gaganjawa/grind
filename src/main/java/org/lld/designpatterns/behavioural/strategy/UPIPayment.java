package org.lld.designpatterns.behavioural.strategy;

public class UPIPayment implements PaymentStrategy {

    private final String bankName;

    public UPIPayment(String bankName) {
        this.bankName = bankName;
    }

    @Override
    public void pay(double amount) {
        System.out.println("Paid " + amount + " through UPI from bank " + bankName);
    }
}
