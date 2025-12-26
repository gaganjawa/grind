package org.lld.designpatterns.creational.prototype.googledoc;

import java.util.Date;

public class Metadata {

    private String author;
    private Date createdAt;
    private Date lastModified;

    public Metadata(String author) {
        this.author = author;
        this.createdAt = new Date();
        this.lastModified = new Date();
    }

    public Metadata(Metadata other) {
        this.author = other.author;
        this.createdAt = new Date(other.createdAt.getTime());
        this.lastModified = new Date(other.lastModified.getTime());
    }
}
