package org.lld.designpatterns.creational.factory.notificationsystem;

import org.junit.jupiter.api.Test;
import org.lld.designpatterns.TestUtils;

import static org.junit.jupiter.api.Assertions.*;

class NotificationFactoryTest {

    @Test
    void testGetNotification_email() {
        Notification n = NotificationFactory.getNotification("email");
        assertTrue(n instanceof EmailNotification);
    }

    @Test
    void testGetNotification_unknown_throws() {
        assertThrows(IllegalArgumentException.class, () -> NotificationFactory.getNotification("bogus"));
    }

    @Test
    void testEmailNotification_send_prints() {
        TestUtils tu = new TestUtils();
        Notification n = NotificationFactory.getNotification("email");

        tu.startCapture();
        n.send("bob@example.com", "hi");
        String out = tu.stopAndGetOutput();

        assertTrue(out.contains("Sending Email to bob@example.com"));
    }
}

