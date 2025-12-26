package org.lld.designpatterns.creational.prototype;

public class SpreadsheetDocument extends Document {

    public SpreadsheetDocument(String title, String content) {
        super(title, content);
    }

    private SpreadsheetDocument(Document other) {
        super(other);
    }

    @Override
    public Document clone() {
        return new SpreadsheetDocument(this);
    }
}
