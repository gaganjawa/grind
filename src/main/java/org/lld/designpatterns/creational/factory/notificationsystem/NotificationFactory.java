package org.lld.designpatterns.creational.factory.notificationsystem;

import static org.lld.designpatterns.creational.factory.notificationsystem.NotificationType.*;

public class NotificationFactory {

    public static Notification getNotification(String type) {
        return switch (NotificationType.valueOf(type.toUpperCase())) {
            case EMAIL -> new EmailNotification();
            case SMS -> new SMSNotification();
            case PUSH -> new PushNotification();
            default -> throw new IllegalArgumentException("Unknown notification type: " + type);
        };
    }
}
