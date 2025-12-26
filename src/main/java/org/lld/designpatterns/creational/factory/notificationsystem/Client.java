package org.lld.designpatterns.creational.factory.notificationsystem;

public class Client {

    public static void main(String[] args) {
        NotificationService notificationService = new NotificationService();

        notificationService.sendNotification("EMAIL", "tootsi@gmail.com", "Hello via Email!");
        notificationService.sendNotification("SMS", "+912223335728", "Hello via SMS!");
        notificationService.sendNotification("PUSH", "asd2389fbk2b1314h8899afff", "Hello via Push Notification!");
    }
}
