package org.lld.designpatterns.creational.prototype.googledoc;

public class Client {

    public static void main(String[] args) {
        PrototypeRegistry registry = new PrototypeRegistry();

        registry.register("TEXT", new TextDocument("Text Template", ""));
        registry.register("SHEET", new TextDocument("Sheet Template", ""));

        Document textDoc1 = (Document) registry.getClone("TEXT");
        textDoc1.setContent("Welcome to Jawa Shared Doc 1.0");

        Document textDoc2 = (Document) registry.getClone("TEXT");
        textDoc2.setContent("Welcome again to Jawa Shared Doc 1.0");

        System.out.println(textDoc1.getContent());
        System.out.println(textDoc2.getContent());
    }
}
