package org.lld.designpatterns.creational.prototype.emailtemplate;

public class WelcomeEmail implements EmailTemplate {

    private String subject;
    private String content;

    public WelcomeEmail() {
        this.subject = "Welcome to Our Service!";
        this.content = "Dear User,\n\nThank you for joining our service. We're excited to have you on board!\n\nBest Regards,\nThe Team";
    }

    @Override
    public EmailTemplate clone() {
        try {
            return (WelcomeEmail) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException("Unlable to clone WelcomeEmail", e);
        }
    }

    @Override
    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public void send(String to) {
        System.out.println("Sending Welcome Email to " + to);
        System.out.println("Subject: [" + subject + "]");
        System.out.println("Content: " + content);
    }
}
