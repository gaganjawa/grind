package org.lld.designpatterns.creational.factory.notificationsystem;

public class EmailNotification implements Notification {
    @Override
    public void send(String recipient, String message) {
        System.out.println("Sending Email to " + recipient + " with message: " + message);
    }
}
