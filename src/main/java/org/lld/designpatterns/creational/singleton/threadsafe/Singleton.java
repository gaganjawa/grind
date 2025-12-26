package org.lld.designpatterns.creational.singleton.threadsafe;

/**
 * The synchronized keyword ensures that only one thread at a time can execute the getInstance() method.
 */
public class Singleton {

    private static Singleton instance;

    private Singleton() {}

    public static synchronized Singleton getInstance() {
        if(instance == null) {
            instance = new Singleton();
        }
        return instance;
    }
}

/**
 * The outer if check avoids synchronization once the instance is created.
 * The inner if inside synchronized ensures that only one thread creates the instance.
 *
 * volatile keyword ensures changes made by one thread are visible to others.
 * Without volatile, one thread might create the Singleton instance, but other threads may not see the updated value due to caching.
 * volatile ensures that the instance is always read from the main memory.
 */
class Singleton2 {
    private static volatile Singleton2 instance;

    private Singleton2() {}

    public static Singleton2 getInstance() {
        if (instance == null) {
            synchronized (Singleton2.class) {
                if (instance == null) {
                    instance = new Singleton2();
                }
            }
        }
        return instance;
    }
}

/**
 * Bill Pugh Singleton (Best Practice for Lazy Loading)
 *
 * Best of both worlds: Lazy + Thread-safe.
 *
 * The Singleton instance is not created until getInstance() is called.
 * The static inner class (Holder) is not loaded until referenced, thanks to Java's class loading mechanism.
 * It ensures thread safety, lazy loading, and high performance without synchronization overhead.
 */
class BillPughSingleton {
    private BillPughSingleton() {}

    private class Holder {
        private static final BillPughSingleton INSTANCE = new BillPughSingleton();
    }

    public static BillPughSingleton getInstance() {
        return Holder.INSTANCE;
    }
}
