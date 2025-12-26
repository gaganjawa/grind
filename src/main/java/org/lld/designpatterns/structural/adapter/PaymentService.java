package org.lld.designpatterns.structural.adapter;

public class PaymentService {

    private final PaymentGateway paymentGateway;

    public PaymentService(PaymentGateway paymentGateway) {
        this.paymentGateway = paymentGateway;
    }

    public String makePayment(double amount, String currency) {
        return paymentGateway.pay(amount, currency);
    }

    public PaymentStatus checkPaymentStatus(String paymentId) {
        return paymentGateway.getStatus(paymentId);
    }
}
