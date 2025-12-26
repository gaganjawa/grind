package org.lld.designpatterns.creational.abstractfactory;

public class Client {

    public static void main(String[] args) {

        CheckoutService checkoutService = new CheckoutService(new IndiaFactory(), "Razorpay");
        checkoutService.checkout(1500.00);

        System.out.println("--------------------------");

        CheckoutService checkoutServiceUS = new CheckoutService(new USAFactory(), "Stripe");
        checkoutServiceUS.checkout(50.00);
    }
}
