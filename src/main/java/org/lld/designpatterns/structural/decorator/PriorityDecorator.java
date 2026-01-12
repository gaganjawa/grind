package org.lld.designpatterns.structural.decorator;

public class PriorityDecorator extends NotificationDecorator {

    private final String priorityLevel;
    public PriorityDecorator(Notification wrappedNotification, String priorityLevel) {
        super(wrappedNotification);
        this.priorityLevel = priorityLevel;
    }

    @Override
    public void notify(String message) {
        String priorityMessage = "[Priority: " + priorityLevel + "] " + message;
        super.notify(priorityMessage);
    }
}
