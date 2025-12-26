package org.lld.designpatterns.creational.prototype.emailtemplate;

import java.util.HashMap;
import java.util.Map;

public class EmailTemplateRegistry {

    private static final Map<String, EmailTemplate> templates = new HashMap<>();

    static {
        templates.put("WELCOME_EMAIL", new WelcomeEmail());
//        templates.put("PASSWORD_RESET_EMAIL", new PasswordResetEmailTemplate());
    }

    public static EmailTemplate getTemplate(String type) {
        EmailTemplate prototype = templates.get(type);
        if (prototype == null) {
            throw new IllegalArgumentException("No template registered for key: " + type);
        }
        return prototype.clone();
    }
}
