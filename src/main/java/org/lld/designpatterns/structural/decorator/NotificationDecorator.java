package org.lld.designpatterns.structural.decorator;

public abstract class NotificationDecorator implements Notification {

    protected  final Notification wrappedNotification;

    public NotificationDecorator(Notification wrappedNotification) {
        this.wrappedNotification = wrappedNotification;
    }

    @Override
    public void notify(String message) {
        wrappedNotification.notify(message);
    }
}
