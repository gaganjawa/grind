package org.lld.designpatterns.creational.factory.notificationsystem;

public class SMSNotification implements Notification {
    @Override
    public void send(String recipient, String message) {
        System.out.println("Sending SMS to " + recipient + " with message: " + message);
    }
}
