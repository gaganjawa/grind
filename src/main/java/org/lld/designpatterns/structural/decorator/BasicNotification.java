package org.lld.designpatterns.structural.decorator;

public class BasicNotification implements Notification {


    @Override
    public void notify(String message) {
        if (message == null || message.isEmpty()) {
            System.out.println("No message to notify.");
        } else {
            System.out.println("Sending Notification: " + message);
        }
    }
}

