package org.lld.designpatterns.structural.decorator;

import java.util.Base64;

public class EncryptedDecorator extends NotificationDecorator {

    public EncryptedDecorator(Notification wrappedNotification) {
        super(wrappedNotification);
    }

    @Override
    public void notify(String message) {
        byte[] encryptedMessage = encrypt(message);
        super.notify(new String(encryptedMessage));
    }

    private static byte[] encrypt(String message) {
        return Base64.getEncoder().encode(message.getBytes());
    }

}
