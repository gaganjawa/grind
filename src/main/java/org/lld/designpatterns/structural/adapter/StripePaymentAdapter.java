package org.lld.designpatterns.structural.adapter;

public class StripePaymentAdapter implements PaymentGateway {

    private final StripeClient stripeClient;

    public StripePaymentAdapter() {
        this.stripeClient = new StripeClient();
    }

    @Override
    public String pay(double amount, String currency) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Amount must be greater than 0");
        }
        if (currency == null || currency.isBlank()) {
            currency = "USD"; // default currency
        }

        System.out.println("Processing payment of " + amount + " " + currency + " through Stripe.");
        int amountInCents = (int) (amount * 100);
        return stripeClient.charge(amountInCents);
    }

    @Override
    public PaymentStatus getStatus(String paymentId) {
        if (paymentId == null || paymentId.isBlank()) {
            throw new IllegalArgumentException("paymentId must not be null or empty");
        }
        System.out.println("Checking payment status for ID: " + paymentId + " through Stripe.");
        boolean isComplete = stripeClient.isPaymentComplete(paymentId);
        return isComplete ? PaymentStatus.COMPLETED : PaymentStatus.PENDING;
    }
}
