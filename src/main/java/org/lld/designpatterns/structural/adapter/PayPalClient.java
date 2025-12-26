package org.lld.designpatterns.structural.adapter;

// PayPal SDK
public class PayPalClient {
    public String makePayment(double amount, String currency) {
        return "PAYPAL_" + System.currentTimeMillis();
    }

    public String checkStatus(String paymentId) {
        if (!paymentId.startsWith("PAYPAL_")) {
            return "FAILED";
        }
        return "COMPLETED"; // COMPLETED / PENDING / FAILED
    }
}
