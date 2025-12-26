package org.lld.designpatterns.creational.abstractfactory;

public class IndiaFactory implements RegionFactory {
    @Override
    public PaymentGateway createPaymentGateway(String gatewayType) {
        if (gatewayType.equalsIgnoreCase("PAYU")) {
            return new PayUgateway();
        } else if (gatewayType.equalsIgnoreCase("RAZORPAY")) {
            return new RazorpayGateway();
        }
        throw new IllegalArgumentException("Unknown payment gateway type: " + gatewayType);
    }

    @Override
    public Invoice createInvoice() {
        return new GSTInvoice();
    }
}
