package main.business.impl;

import main.business.impl.reader.OnlineReader;
import main.business.impl.reader.format.Formatter;
import main.business.service.BookBusinessService;
import main.business.service.ReaderBusinessService;
import main.common.book.Book;

public class ReaderBusinessServiceImpl implements ReaderBusinessService {

    private static final ReaderBusinessService singleton = new ReaderBusinessServiceImpl();

    public static ReaderBusinessService getInstance() {
        return singleton;
    }

    private final BookBusinessService bookBusinessService = BookBusinessServiceImpl.getInstance();

    private ReaderBusinessServiceImpl() { }

    @Override
    public String show(String isbn) {
        return show(bookBusinessService.findByISBN(isbn));
    }

    @Override
    public String show(Book b) {
        String content = "";
        return new OnlineReader().show(content);
    }

    @Override
    public String show(String isbn, Formatter formatter) {
        return show(bookBusinessService.findByISBN(isbn), formatter);
    }

    @Override
    public String show(Book b, Formatter formatter) {
        String content = "";
        return new OnlineReader(formatter).show(content);
    }

}
