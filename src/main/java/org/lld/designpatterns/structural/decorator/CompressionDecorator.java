package org.lld.designpatterns.structural.decorator;

public class CompressionDecorator extends NotificationDecorator {


    public CompressionDecorator(Notification wrappedNotification) {
        super(wrappedNotification);
    }

    @Override
    public void notify(String message) {
        String compressedMessage = compress(message);
        super.notify(compressedMessage);
    }

    private static String compress(String message) {
        return message.replaceAll("\\s+", "");
    }
}
