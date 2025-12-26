package org.lld.designpatterns.creational.abstractfactory;

public class GSTInvoice implements Invoice {
    @Override
    public void generateInvoice() {
        System.out.println("Generating GST Invoice.");
    }
}
