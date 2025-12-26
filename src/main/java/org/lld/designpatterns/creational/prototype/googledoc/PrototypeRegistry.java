package org.lld.designpatterns.creational.prototype.googledoc;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class PrototypeRegistry {

    private final Map<String, Document> registry = new ConcurrentHashMap<>();

    public void register(String key, Document prototype) {
        registry.put(key, prototype);
    }

    public Document getClone(String key) {

        Document prototype = registry.get(key);
        if(prototype == null) {
            throw new IllegalArgumentException("No Prototype registered for " + key);
        }
        return prototype.clone();
    }
}
