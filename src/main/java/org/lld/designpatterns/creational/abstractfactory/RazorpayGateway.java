package org.lld.designpatterns.creational.abstractfactory;

public class RazorpayGateway implements PaymentGateway {
    @Override
    public void processPayment(double amount) {
        System.out.println("Processing payment of INR " + amount + " through Razorpay.");
    }
}
