package org.lld.designpatterns.creational.abstractfactory;

public class CheckoutService {
    private PaymentGateway paymentGateway;
    private Invoice invoice;
    private String gatewayType;

    public CheckoutService(RegionFactory factory, String gatewayType) {
        this.paymentGateway = factory.createPaymentGateway(gatewayType);
        this.invoice = factory.createInvoice();
        this.gatewayType = gatewayType;
    }

    public void checkout(double amount) {
        System.out.println("Starting checkout with " + gatewayType + " gateway.");
        paymentGateway.processPayment(amount);
        invoice.generateInvoice();
        System.out.println("Checkout completed.");
    }
}
