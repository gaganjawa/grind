package org.lld.designpatterns.creational.prototype.emailtemplate;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EmailTemplateRegistryTest {

    @Test
    void testGetTemplate_returnsCloneNotSame() {
        EmailTemplate a = EmailTemplateRegistry.getTemplate("WELCOME_EMAIL");
        EmailTemplate b = EmailTemplateRegistry.getTemplate("WELCOME_EMAIL");

        assertNotNull(a);
        assertNotNull(b);
        assertNotSame(a, b);
    }

    @Test
    void testGetTemplate_unknown_throws() {
        assertThrows(IllegalArgumentException.class, () -> EmailTemplateRegistry.getTemplate("UNKNOWN"));
    }
}

