package org.lld.designpatterns.creational.singleton;

/**
 * The instance starts as null.
 * It is only created when getInstance() is first called.
 * Future calls return the already created instance.
 *
 * Object creation is deferred until required.
 *
 * Lazy Loading is Not thread-safe by default.
 */
public class LazySingleton {

    private static LazySingleton instance;

    private LazySingleton() {
    }

    public static LazySingleton getInstance() {
        if(instance == null) {
            instance = new LazySingleton();
        }
        return instance;
    }
}
