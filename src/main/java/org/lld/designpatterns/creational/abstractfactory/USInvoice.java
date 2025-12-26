package org.lld.designpatterns.creational.abstractfactory;

public class USInvoice implements Invoice {
    @Override
    public void generateInvoice() {
        System.out.println("Generating US Invoice.");
    }
}
