package org.lld.designpatterns.structural.adapter;

public class PayPalPaymentAdapter implements PaymentGateway {
    private final PayPalClient payPalClient;

    public PayPalPaymentAdapter() {
        this.payPalClient = new PayPalClient();
    }

    @Override
    public String pay(double amount, String currency) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Amount must be greater than 0");
        }
        if (currency == null || currency.isBlank()) {
            currency = "USD";
        }
        System.out.println("Processing payment of " + amount + " " + currency + " through PayPal.");
        return payPalClient.makePayment(amount, currency);
    }

    @Override
    public PaymentStatus getStatus(String paymentId) {
        if (paymentId == null || paymentId.isBlank()) {
            throw new IllegalArgumentException("paymentId must not be null or empty");
        }
        System.out.println("Checking payment status for ID: " + paymentId + " through PayPal.");
        return switch (payPalClient.checkStatus(paymentId)) {
            case "COMPLETED" -> PaymentStatus.COMPLETED;
            case "PENDING" -> PaymentStatus.PENDING;
            case "FAILED" -> PaymentStatus.FAILED;
            default -> PaymentStatus.FAILED; // map unknown statuses to FAILED
        };
    }
}
