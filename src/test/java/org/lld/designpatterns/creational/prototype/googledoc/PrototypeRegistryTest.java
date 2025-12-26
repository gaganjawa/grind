package org.lld.designpatterns.creational.prototype.googledoc;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PrototypeRegistryTest {

    @Test
    void testRegisterAndGetClone_returnsDistinctClone() {
        PrototypeRegistry reg = new PrototypeRegistry();
        SpreadsheetDocument proto = new SpreadsheetDocument("Title", "Content");
        reg.register("sheet", proto);

        Document clone = reg.getClone("sheet");
        assertNotNull(clone);
        assertNotSame(proto, clone);
        assertEquals("Content", clone.getContent());
    }

    @Test
    void testCloneIsDeep_independentModification() {
        PrototypeRegistry reg = new PrototypeRegistry();
        SpreadsheetDocument proto = new SpreadsheetDocument("Title", "Content");
        reg.register("sheet2", proto);

        Document clone = reg.getClone("sheet2");
        clone.setContent("NewContent");

        Document clone2 = reg.getClone("sheet2");
        assertEquals("Content", clone2.getContent());
    }

    @Test
    void testGetClone_unknownKey_throws() {
        PrototypeRegistry reg = new PrototypeRegistry();
        assertThrows(IllegalArgumentException.class, () -> reg.getClone("nope"));
    }
}

