package org.lld.designpatterns.creational.prototype.googledoc;

import java.util.UUID;

public abstract class Document {
    String id;
    String title;
    String content;
    Metadata metadata;
    Permissions permissions;

    public Document(Document other) {
        this.id = UUID.randomUUID().toString();
        this.title = other.title;
        this.content = other.content;
        this.metadata = new Metadata(other.metadata);
        this.permissions = new Permissions(other.permissions);
    }

    public Document(String title, String content) {
        this.id = UUID.randomUUID().toString();
        this.title = title;
        this.content = content;
        this.metadata = new Metadata("system");
        this.permissions = new Permissions();
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public abstract Document clone();
}
