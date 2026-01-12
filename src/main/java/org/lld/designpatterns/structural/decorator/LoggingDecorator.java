package org.lld.designpatterns.structural.decorator;

public class LoggingDecorator extends NotificationDecorator {


    public LoggingDecorator(Notification wrappedNotification) {
        super(wrappedNotification);
    }

    @Override
    public void notify(String message) {
        System.out.println("[LOG] : About to send notification with message: " + message);
        super.notify(message);
    }
}
