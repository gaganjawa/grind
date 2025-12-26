package org.lld.designpatterns.creational.abstractfactory;

public interface RegionFactory {
    PaymentGateway createPaymentGateway(String gatewayType);
    Invoice createInvoice();
}
