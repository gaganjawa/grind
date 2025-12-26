package org.lld.designpatterns.creational.abstractfactory;

public class USAFactory implements RegionFactory {
    @Override
    public PaymentGateway createPaymentGateway(String gatewayType) {
        if (gatewayType.equalsIgnoreCase("PayPal")) {
            return new PayPalGateway();
        } else if (gatewayType.equalsIgnoreCase("Stripe")) {
            return new StripeGateway();
        }
        throw new IllegalArgumentException("Unknown payment gateway type: " + gatewayType);
    }

    @Override
    public Invoice createInvoice() {
        return new USInvoice();
    }
}
