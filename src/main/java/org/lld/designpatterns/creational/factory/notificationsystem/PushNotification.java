package org.lld.designpatterns.creational.factory.notificationsystem;

public class PushNotification implements Notification {
    @Override
    public void send(String recipient, String message) {
        System.out.println("Sending Push Notification to " + recipient + " with message: " + message);
    }
}
