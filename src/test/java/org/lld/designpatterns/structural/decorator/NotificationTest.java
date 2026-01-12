package org.lld.designpatterns.structural.decorator;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class NotificationTest {

    private String captureOutput(Runnable r) {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(out);
        PrintStream old = System.out;
        try {
            System.setOut(ps);
            r.run();
            ps.flush();
            return out.toString().trim();
        } finally {
            System.setOut(old);
        }
    }

    @Test
    void testBasicNotification_printsMessage() {
        String out = captureOutput(() -> {
            Notification notification = new BasicNotification();
            notification.notify("This is a basic notification.");
        });
        Assertions.assertTrue(out.contains("Sending Notification: This is a basic notification."));
    }

    @Test
    void testBasicNotification_emptyMessage() {
        String out = captureOutput(() -> {
            Notification notification = new BasicNotification();
            notification.notify("");
        });
        Assertions.assertTrue(out.contains("No message to notify."));
    }

    @Test
    void testLoggingDecorator_logsThenSends() {
        String out = captureOutput(() -> {
            Notification n = new LoggingDecorator(new BasicNotification());
            n.notify("Hello");
        });
        int logIndex = out.indexOf("[LOG] : About to send notification with message: Hello");
        int sendIndex = out.indexOf("Sending Notification: Hello");
        Assertions.assertTrue(logIndex >= 0, "Expected log line present");
        Assertions.assertTrue(sendIndex >= 0, "Expected send line present");
        Assertions.assertTrue(logIndex < sendIndex, "Log should appear before send");
    }

    @Test
    void testEncryptedDecorator_encodes() {
        String out = captureOutput(() -> {
            Notification n = new EncryptedDecorator(new BasicNotification());
            n.notify("abc");
        });
        Assertions.assertTrue(out.contains("Sending Notification: YWJj"));
    }

    @Test
    void testCompressionAndEncryptionStacking() {
        String out = captureOutput(() -> {
            Notification n = new EncryptedDecorator(new CompressionDecorator(new BasicNotification()));
            n.notify("a b c");
        });
        // compressed becomes "abc" -> base64 "YWJj"
        Assertions.assertTrue(out.contains("Sending Notification: YSBiIGM="));
    }

    @Test
    void testPriorityDecorator_prefixesPriority() {
        String out = captureOutput(() -> {
            Notification n = new PriorityDecorator(new BasicNotification(), "HIGH");
            n.notify("Server down");
        });
        Assertions.assertTrue(out.contains("Sending Notification: [Priority: HIGH] Server down"));
    }
}
