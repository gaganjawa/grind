package org.lld.designpatterns;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class TestUtils {
    private final ByteArrayOutputStream baos = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    public void startCapture() {
        System.setOut(new PrintStream(baos));
    }

    public String stopAndGetOutput() {
        System.out.flush();
        System.setOut(originalOut);
        return baos.toString();
    }
}

