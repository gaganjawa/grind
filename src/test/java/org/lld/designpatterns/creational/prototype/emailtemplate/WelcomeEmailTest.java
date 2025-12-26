package org.lld.designpatterns.creational.prototype.emailtemplate;

import org.junit.jupiter.api.Test;
import org.lld.designpatterns.TestUtils;

import static org.junit.jupiter.api.Assertions.*;

class WelcomeEmailTest {

    @Test
    void testCloneIndependent_modifyingCloneDoesNotAffectPrototype() {
        EmailTemplate clone1 = EmailTemplateRegistry.getTemplate("WELCOME_EMAIL");
        clone1.setContent("Custom Content");

        EmailTemplate clone2 = EmailTemplateRegistry.getTemplate("WELCOME_EMAIL");

        // clone2 should have the default content (not "Custom Content"). We'll check via send output contains default subject
        TestUtils tu = new TestUtils();
        tu.startCapture();
        clone2.send("john@example.com");
        String out = tu.stopAndGetOutput();

        assertTrue(out.contains("Welcome to Our Service!"));
        assertFalse(out.contains("Custom Content"));
    }
}

