package main.business.impl.reader;

import main.business.impl.reader.format.Formatter;
import main.business.impl.reader.format.PDFFormatter;

public class OnlineReader {

    private Formatter formatter;

    public OnlineReader() {
        this(new PDFFormatter());
    }

    public OnlineReader(Formatter formatter) {
        this.formatter = formatter;
    }

    public void setFormatter(Formatter formatter) {
        this.formatter = formatter;
    }

    public String show(String doc) {
        return formatter.format(doc) + System.lineSeparator() + doc;
    }

}
