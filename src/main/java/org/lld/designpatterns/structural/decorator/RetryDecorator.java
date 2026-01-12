package org.lld.designpatterns.structural.decorator;

public class RetryDecorator extends NotificationDecorator {

    private final int maxRetries;

    public RetryDecorator(Notification wrappedNotification, int maxRetries) {
        super(wrappedNotification);
        this.maxRetries = maxRetries;
    }

    @Override
    public void notify(String message) {
        int attempt = 0;
        while(attempt < maxRetries) {
            try {
                attempt++;
                super.notify(message);
                return;
            } catch (Exception e) {
                System.out.println("Retry attempt " + attempt + " failed: " + e.getMessage());
            }
        }
        System.out.println("Notification failed after " + maxRetries + " attempts.");
    }
}
