package org.lld.designpatterns.structural.adapter;

public class RazorpayPaymentGateway implements PaymentGateway {

    @Override
    public String pay(double amount, String currency) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Amount must be greater than 0");
        }
        if (currency == null || currency.isBlank()) {
            currency = "INR"; // default currency for Razorpay
        }
        // Simulate payment processing with Razorpay
        System.out.println("Processing payment of " + amount + " " + currency + " through Razorpay.");
        return "RAZORPAY12345"; // Simulated payment ID
    }

    @Override
    public PaymentStatus getStatus(String paymentId) {
        if (paymentId == null || paymentId.isBlank()) {
            throw new IllegalArgumentException("paymentId must not be null or empty");
        }
        // Simulate checking payment status with Razorpay
        System.out.println("Checking payment status for ID: " + paymentId + " through Razorpay.");
        return PaymentStatus.COMPLETED; // Simulated status
    }
}
