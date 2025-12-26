package org.lld.designpatterns.structural.adapter;

public interface PaymentGateway {

    String pay(double amount, String currency);

    PaymentStatus getStatus(String paymentId);
}
