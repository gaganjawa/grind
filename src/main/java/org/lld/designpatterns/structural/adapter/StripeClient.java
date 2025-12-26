package org.lld.designpatterns.structural.adapter;

// Stripe SDK (external)
public class StripeClient {

    public String charge(int amountInCents) {
        return "STRIPE_" + System.currentTimeMillis();
    }

    public boolean isPaymentComplete(String transactionId) {
        return true;
    }
}
