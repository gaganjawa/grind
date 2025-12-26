package org.lld.designpatterns.creational.singleton;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LazySingletonTest {

    @Test
    void testGetInstance_sameReference() {
        LazySingleton a = LazySingleton.getInstance();
        LazySingleton b = LazySingleton.getInstance();

        assertNotNull(a);
        assertSame(a, b);
    }
}

