package org.lld.designpatterns.creational.prototype;

public class TextDocument extends Document {

    public TextDocument(String title, String content) {
        super(title, content);
    }

    private TextDocument(TextDocument other) {
        super(other);
    }

    @Override
    public Document clone() {
        return new TextDocument(this);
    }
}
