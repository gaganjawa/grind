package org.lld.designpatterns.creational.singleton;

/**
 * The object is created immediately when the class is loaded.
 * It's always available and inherently thread-safe.
 */
public class EagerSingleton {

    private static final EagerSingleton instance = new EagerSingleton();

    private EagerSingleton() {
    }

    public static EagerSingleton getInstance() {
        return instance;
    }
}
