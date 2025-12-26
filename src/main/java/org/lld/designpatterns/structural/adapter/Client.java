package org.lld.designpatterns.structural.adapter;

import org.JacocoIgnore;

@JacocoIgnore
public class Client {

    public static void main(String[] args) {
        PaymentGateway paymentGateway = new PayPalPaymentAdapter();

        PaymentService paymentService = new PaymentService(paymentGateway);

        String paymentId = paymentService.makePayment(100.0, "USD");
        System.out.println(paymentId);

        PaymentStatus status = paymentService.checkPaymentStatus(paymentId);
        System.out.println("Payment Status: " + status);

        System.out.println("---------------------------");

        paymentService = new PaymentService(new RazorpayPaymentGateway());
        paymentId = paymentService.makePayment(200.0, "INR");
        System.out.println(paymentId);

        status = paymentService.checkPaymentStatus(paymentId);
        System.out.println("Payment Status: " + status);
    }
}
