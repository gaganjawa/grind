package org.lld.designpatterns.creational.prototype.emailtemplate;

public class Client {

    public static void main(String[] args) {
        EmailTemplate welcomeEmail = EmailTemplateRegistry.getTemplate("WELCOME_EMAIL");
        welcomeEmail.setContent("Hello, welcome to our service!");
        welcomeEmail.send("gagan@gaganjawa.com");

        EmailTemplate anotherWelcomeEmail = EmailTemplateRegistry.getTemplate("WELCOME_EMAIL");
        anotherWelcomeEmail.setContent("Hi there, we're glad to have you!");
        anotherWelcomeEmail.send("jawa@gaganjawa.com");
    }
}
