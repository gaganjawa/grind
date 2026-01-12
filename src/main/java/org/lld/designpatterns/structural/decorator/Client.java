package org.lld.designpatterns.structural.decorator;

public class Client {

    public static void main(String[] args) {
        Notification notification = new BasicNotification();
        notification.notify("Hello, this is a basic notification.");

        Notification compressedNotification = new CompressionDecorator(new BasicNotification());
        compressedNotification.notify("Hello, this is a compressed notification.");

        Notification allNotification =
                new LoggingDecorator(
                        new EncryptedDecorator(
                                new CompressionDecorator(
                                        new PriorityDecorator(
                                                new BasicNotification(), "HIGH"
                                        )
                                )
                        )
                );
        allNotification.notify("Order placed successfully");
    }
}
