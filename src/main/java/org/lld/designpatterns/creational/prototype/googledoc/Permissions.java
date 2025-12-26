package org.lld.designpatterns.creational.prototype.googledoc;

import java.util.HashSet;
import java.util.Set;

public class Permissions {

    Set<String> readUsers = new HashSet<>();
    Set<String> writeUsers = new HashSet<>();

    public Permissions() {
    }

    public Permissions(Permissions other) {
        this.readUsers = new HashSet<>(other.readUsers);
        this.writeUsers = new HashSet<>(other.writeUsers);
    }
}
